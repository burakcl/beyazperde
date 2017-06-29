import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "http://www.beyazperde.com/filmler/tum-filmleri/kullanici-puani/";
        filimsi(url);
    }

    public static void filimsi(String url) throws IOException{
            for (int i=1; i<=831; i++){
                url = "http://www.beyazperde.com/filmler/tum-filmleri/kullanici-puani/?page="+i;
                Document document = Jsoup.connect(url).get();
                Elements linkler = document.select("a[class~=no_underline]");
                for(Element j:linkler){
                    System.out.println("---------------------------");
                    System.out.println(j.text());
                    System.out.println("---------------------------");
                    String yorum_sayfa = "http://www.beyazperde.com"+j.attr("href")+"kullanici-elestirileri/";
                    sayfa(yorum_sayfa);
                }
            }
        }

    public static void sayfa(String url) throws IOException{
        Document document = Jsoup.connect(url).get();
        Elements s = document.getElementsByClass("pagination-item-holder").tagName("span");
        for (Element a:s){
            for ( int i=1; i<= Integer.parseInt(a.children().last().text()); i++) {
                String yorum = url + "?page=" +i ;
                yorumla(yorum);
                System.out.println("---------------"+i+"--------------");
            }
        }
    }

    public static void yorumla(String yorum) throws IOException{
        Document document = Jsoup.connect(yorum).get();
        Elements yorumlar = document.select("p[itemprop~=description]");
        for (Element j:yorumlar){
            System.out.println(j.text());
        }
    }
}
