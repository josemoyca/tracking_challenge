package demo.retail.inventory.handlers.bus;

import com.google.gson.Gson;
import demo.retail.inventory.config.RabbitConfig;
import demo.retail.inventory.drivenAdapters.repository.IMovementRepository;
import demo.retail.inventory.drivenAdapters.repository.IRecordRepository;
import demo.retail.inventory.models.Movement;
import demo.retail.inventory.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

@Component
public class RabbitMqMessageConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Gson gson;
    @Autowired
    private IRecordRepository recordRepository;
    @Autowired
    private IMovementRepository movementRepository;

    @Override
    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_MOVEMENTS)
                .map(message -> {
                    Movement movement = gson
                            .fromJson(new String(message.getBody()),
                                    Movement.class);

                    movementRepository.save(movement).subscribe();

                    System.out.println("Movimiento registrado:  " + movement);
                    return movement;
                }).subscribe();

        receiver.consumeAutoAck(RabbitConfig.QUEUE_RECORD)
                .map(message -> {
                    Record record = gson
                            .fromJson(new String(message.getBody()),
                                    Record.class);

                    recordRepository.save(record).subscribe();
                    System.out.println("Registro almacenado: " + record);
                    return record;
                }).subscribe();
    }

}
