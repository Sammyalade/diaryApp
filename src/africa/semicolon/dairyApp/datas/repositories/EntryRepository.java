package africa.semicolon.dairyApp.datas.repositories;

import africa.semicolon.dairyApp.datas.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<Entry, String> {

}
