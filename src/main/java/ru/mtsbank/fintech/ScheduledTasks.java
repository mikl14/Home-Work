package ru.mtsbank.fintech;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.database.DatabaseConnection;
import ru.mtsbank.fintech.entity.Animal;
import ru.mtsbank.fintech.entity.AnimalType;
import ru.mtsbank.fintech.entity.Breed;
import ru.mtsbank.fintech.entity.Creature;
import ru.mtsbank.fintech.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

@Component
public class ScheduledTasks {
    private DatabaseConnection databaseConnection;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        createCreature("test",1,2);
    }

    /**
     * <b>databaseScheduledLog</b>
     * Выводит все записи из таблиц в базе раз в 20 секунд
     */
    @Scheduled(fixedRate = 1000 * 20) // 20 секунд
    public void databaseScheduledLog() {

        try {
            log.info("Base Record of Animals: " + getAllCreatures());
            //log.info("Base Record of Provides: " + databaseConnection.getProviders().toString());
        } catch (Exception e) {
            log.error("Exception! : " + e.getMessage(), e);
        }
    }

    public void createCreature(String name, int id, int age) {
        // Создание сессии Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // Начало транзакции
            transaction = session.beginTransaction();

            for(int i = 0; i < 10;i++)
            {
                Breed breed = new Breed(new ArrayList<Animal>());
                session.save(breed);
                AnimalType animalType = new AnimalType("CAT "+i,false);
                session.save(animalType);
                Creature creature = new Creature("viktor "+i,1,12+i);
                session.save(creature);
                // Создание объекта Creature
                Animal animal = new Animal(breed,animalType,creature);

                // Сохранение объекта Creature в базе данных
                session.save(animal);
            }


            // Завершение транзакции
            transaction.commit();
        } catch (Exception e) {
            // Откат транзакции в случае ошибки
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Закрытие сессии Hibernate
            session.close();
        }
    }

    public List<Animal> getAllCreatures() {
        // Создание сессии Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animal> animals = new ArrayList<>();

        try {
            // Получение всех объектов Creature из таблицы
            animals = session.createQuery("FROM Animal", Animal.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие сессии Hibernate
            session.close();
        }

        return animals;
    }
}