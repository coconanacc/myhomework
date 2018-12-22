package Messageboard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class InfoServlet extends HttpServlet {
    MessageBoardService messageBoardService;

    @Override
    public  void init(){messageBoardService = MessageBoardServiceImpl.getInstance();}

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        List<Message> messageList = messageBoardService.findAllMessages();
        String res = messageBoardService.messagesToJson(messageList);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        resp.getOutputStream()
                )
        );
        writer.write(res);
        writer.flush();
        writer.close();

    }
}
