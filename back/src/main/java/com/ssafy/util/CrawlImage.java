package com.ssafy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlImage {

//	public static void main(String[] args) {
////		String imgUrl = doCrawlImage("광화문풍림스페이스본(101동~105동)");
//		String imgUrl = "https://img.freepik.com/free-photo/beautiful-landscape-and-cityscape-of-seoul-city_74190-3198.jpg?w=1060&t=st=1668991073~exp=1668991673~hmac=90836c10c4926b261096d9f282cce7b076d11b8bf202b48deb10925c36358848";
//		System.out.println(imgUrl);
//	}
	

	static public String doCrawlImage(String aptName) { 
//		final String naverImgUrl = "https://www.google.com/search?q=" + aptName+"&tbm=isch";
		final String naverImgUrl = "https://kr.freepik.com/photos/%EC%95%84%ED%8C%8C%ED%8A%B8";
//		System.out.println(naverImgUrl);
		Connection conn = Jsoup.connect(naverImgUrl);
		List<String> aptImages = new ArrayList<>();
		try {
			Elements elements = conn.timeout(3000).get().select("img.min-size-to-snippet");
			for (Element element : elements) {
				String src = element.attr("src");
				if(!src.equals(""))
					aptImages.add(src);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Random random = new Random();	
		return aptImages.size() == 0 ? null : aptImages.get(random.nextInt(aptImages.size()));
	}

}
