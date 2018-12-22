package Messageboard;

import java.util.List;

public interface MessageBoardDao {
    List<Message> findMessagesByPid(int pid);
    void insertMessage(Message message);
}
