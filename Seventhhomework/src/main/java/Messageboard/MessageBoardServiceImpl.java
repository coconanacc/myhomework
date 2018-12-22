package Messageboard;

import java.util.List;

public class MessageBoardServiceImpl<instance> implements MessageBoardService{
    private static MessageBoardService instance = null;
    private static MessageBoardDao messageBoardDao = null;
    public MessageBoardServiceImpl() {
        messageBoardDao = MessageBoardDaoImpl.getInstance();
    }
    public static MessageBoardService getInstance(){
        if(instance == null)
            synchronized (MessageBoardServiceImpl.class) {
                if (instance == null) {
                    instance = new MessageBoardServiceImpl();
                }
            }

        }
    return instance;
    }
    private List<Message> findContentChild(Message content) {
        List<Message> list = messageBoardDao.findMessagesByPid(content.getId());
        for (Message message : list) {
            List<Message> childList = findContentChild(message);
            message.setChildContent(childList);
        }
        return list;
    }

    public List<Message> findAllMessages() {
        List<Message> list = messageBoardDao.findMessagesByPid(0);
        for (Message message : list) {
            List<Message> childList = findContentChild(message);
            message.setChildContent(childList);
        }

        return list;
    }

    public String messagesToJson(List<Message> messageList) {
        return null;
    }


    @Override
    public String messageToJson(List<Message> messageList) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":10000,\"data\":[");
        if (messageList != null && messageList.size() != 0) {
            for (Message content : messageList) {
                sb.append(createJson(content));
                sb.append(",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        sb.append("]}");

        return sb.toString();
    }




    @Override
    public boolean insertContent(Message message) {

        if (message.getUsername() != null && message.getText() != null) {

            messageBoardDao.insertMessage(message);
            return true;

        }

        return false;

    }

    private String createJson(Message message) {

        StringBuffer sb = new StringBuffer();

        sb.append("{\"id\":").append(message.getId())
                .append(",\"username\":\"").append(message.getUsername())
                .append("\",\"text\":\"").append(message.getText())
                .append("\"").append(",\"child\":[");


        List<Message> child = message.getChildContent();

        for (Message content : child) {

            String json = createJson(content);

            sb.append(json).append(",");

        }

        if (sb.charAt(sb.length() - 1) == ',') {
            sb.delete(sb.length() - 1, sb.length());
        }

        sb.append("]}");

        return sb.toString();
    }


}

