package test;
//import services.Message_tools;
import tools.Message_tools;


public class Message_test {
	public static void main(String[] args) throws Exception {
		
		Message_tools.addMessage(1,"steve", "comment tu vas eddy?");
		Message_tools.addMessage(1,"steve", "r�ponds bouffon");
		Message_tools.addMessage(2,"eddy", "d�sol� je m'excuse");
		Message_tools.getListMessage(1);
		System.out.println("on tente de supprimer le 1 \n ------------------------------- \n");
		Message_tools.deleteMessage(1);
		System.out.println("J'ai effac�? \n");
		Message_tools.getListMessage(1);
		System.out.println("Update ------------------------------");
		Message_tools.setMessage(3, "J ai modifi� le texte");
		Message_tools.getListMessage(2);


	}
}
