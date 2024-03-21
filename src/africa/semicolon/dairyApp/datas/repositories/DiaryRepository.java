package africa.semicolon.dairyApp.datas.repositories;


import africa.semicolon.dairyApp.datas.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {

}
