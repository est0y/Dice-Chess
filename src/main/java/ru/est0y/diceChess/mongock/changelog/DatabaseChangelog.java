package ru.est0y.diceChess.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.est0y.diceChess.domain.security.User;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    //password:1
    private final User editor = new User("user1",
            "$2a$12$qKHd09USqIp8f/FgzYe8r.7lEUCBEp3cCEYOEZWd1gCjtwGQSsbo6",
            false,
            "ROLE_USER"
    );

    private final User user = new User("user2",
            "$2a$12$qKHd09USqIp8f/FgzYe8r.7lEUCBEp3cCEYOEZWd1gCjtwGQSsbo6",
            false,
            "ROLE_USER"
    );


    @ChangeSet(order = "001", id = "dropDb", author = "ru/est0y", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertUser", author = "ru/est0y")
    public void insertUser(MongockTemplate mongockTemplate) {
          mongockTemplate.insertAll(List.of(user,editor));
    }


}