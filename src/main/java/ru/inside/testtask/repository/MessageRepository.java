package ru.inside.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inside.testtask.entity.Message;
import ru.inside.testtask.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUserOrderByCreatedDateDesc(User user);
}
