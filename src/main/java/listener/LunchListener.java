package listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LunchListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String msg = event.getMessage().getContentRaw();
		
		if(msg.startsWith("!yy")) {
			int idx = msg.indexOf(" "); //첫번째로 나오는 공백을 찾아서
			if(idx < 0) {
				sayMsg("안녕하세요 스킬봇입니다. 도움을 원하시면 !yy help를 입력하세요", event);
				return;
			}
			//!yy echo dfaf
			String cmd = msg.substring(idx + 1);
			idx = cmd.indexOf(" ");
			String param = "";
			if(idx >= 0) {
				//파라메터가 들어왔다면 명령어와 파라메터를 구분한다.
				param = cmd.substring(idx + 1);
				cmd = cmd.substring(0, idx);
			}
			
			switch(cmd) {
			case "echo":
				if(param == "") {
					sayMsg("echo 명령어는 메아리 할 말을 입력하셔야 합니다.", event);
				}else {
					sayMsg(param, event);
				}
				break;
			case "help":
				sayMsg("이 봇은 다음과 같은 명령어를 사용할 수 있습니다.", event);
				break;
			}
			
		}
	}
	private void sayMsg(String msg, MessageReceivedEvent e) {
		e.getChannel().sendMessage(msg).queue();
	}
}
