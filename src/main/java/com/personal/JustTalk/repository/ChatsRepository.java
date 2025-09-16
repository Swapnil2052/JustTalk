package com.personal.JustTalk.repository;

import com.personal.JustTalk.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Long> {
    @Query("SELECT DISTINCT c FROM Chats c " +
            "JOIN c.users u " + // to filter chats containing current user
            "LEFT JOIN FETCH c.users allUsers " + // to fetch all participants
            "WHERE u.username = :username")
    List<Chats> findChatsByUsernameWithDetails(@Param("username") String username);

    @Query("SELECT c FROM Chats c " +
            "JOIN c.users u " +
            "LEFT JOIN FETCH c.users allUsers " +
            "LEFT JOIN FETCH c.messages m " +
            "LEFT JOIN FETCH m.sender " +
            "WHERE c.id = :id AND u.username = :username")
    Optional<Chats> findChatByIdAndUsernameWithDetails(Long id, String username);
}