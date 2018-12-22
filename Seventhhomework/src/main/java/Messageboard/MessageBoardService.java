package Messageboard;

import java.util.List;

public interface MessageBoardService {
    List<Message> findAllMessages();

    String messagesToJson(List<Message> messageList);
}
