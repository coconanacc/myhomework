package Messageboard;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SendServlet extends HttpServlet {
    private static final String ERROR="{\"status\":\"10001\"}";
    private static final String OK="{\"status\":\"10000\"}";
    private MessageBoardService messageBoardService;
    @Override
    public void init() {
        messageBoardService = MessageBoardServiceImpl.getInstance();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String text = request.getParameter("text");
        int pid = Integer.parseInt(request.getParameter("pid"));

        Message message = new Message(username, text, pid);

        String res = ERROR;
        if (messageBoardService.insertContent(message)) {
            res = OK;
        }

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        response.getOutputStream()
                )
        );

        writer.write(res);
        writer.flush();
        writer.close();
    }
}
