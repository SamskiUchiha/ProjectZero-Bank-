package com.ex;

import com.ex.model.Transaction;
import com.ex.model.User;
import com.ex.repository.Repository;
import com.ex.repository.UserRepository;
import com.ex.screen.Screen;
import com.ex.screen.WelcomeScreen;
import com.ex.services.DataService;
import com.mongodb.MongoClientSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.HashMap;

public class BankApp extends AbstractApp {
    private Screen currentScreen;
    public static final Logger logger = LogManager.getLogger(BankApp.class.getName());

    /**
     * Initializing the current screen to the welcome screen
     */
    public BankApp() {
        this.currentScreen = new WelcomeScreen();
    }

    /**
     * Initializing the application and connect repo to the mongoDB
     */
    private void initialize() {
        logger.info("Initializing Bank Application");
        context = new HashMap<>();

        MongoConnector connector = new MongoConnector();
        connector.configure( () -> {
            CodecProvider codecProvider = PojoCodecProvider.builder().register("com.ex.model").build();
            CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            return MongoClientSettings.builder()
                    .applyConnectionString(connector.newConectionString("mongodb://localhost:27017/bank"))
                    .retryWrites(true)
                    .codecRegistry(registry)
                    .build();
        }).createClient();

        Repository<Transaction, User, ObjectId> repo = new UserRepository(connector);
        DataService service = new DataService(repo);

        context.put("Service", service);
        context.put("Repo", repo);
    }

    /**
     * Calls initialize() and run the screens
     * @param args not really sure what this do
     * @throws IOException
     */
    @Override
    public void run(String[] args) throws IOException {
        initialize();
        while (currentScreen != null) {
            currentScreen = currentScreen.makeScreen(this);
        }
    }
}
