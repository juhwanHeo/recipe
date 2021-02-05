package egovframework.recipe.utils;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import egovframework.recipe.vo.RecipeVO;

public class RecipeUtils {

	public static List<String> getRecipeIdList(String irdnt_nm){
		String svcKey = "Grid_20150827000000000227_1"; // 레시피 재료 정보 조회 서비스
		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
		String startIdx = "1"; // 레시피 재료 시작 순번
		String endIdx = "5"; // 레시피 재료 종료 순번 - 전체 순번 몰라서 일단 5로
		List<String> recipeIdList = null;
		try {
			String encodeResult = URLEncoder.encode(irdnt_nm, "utf-8");
			String uri = "http://211.237.50.150:7080/openapi/" + apiKey + "/json/" + svcKey + "/" 
					+ startIdx + "/" + endIdx+"/?IRDNT_NM=" + encodeResult;
		
			// System.out.println("encodeResult: " + encodeResult);
			URL url = new URL(uri);
			// startIdx, endIdx를 몰라도 넣어줘야하도록 url이 설계되어있음
			// 1. URLConnection 객체 생성
			URLConnection urlConnection = url.openConnection();
			// 2. Parsing을 위한 Parser객체 생성
			JSONParser parser = new JSONParser();
			// 3. JSON 문자열 데이터를 읽어오려고 보조스트림 InputStreamReader 활용
			Object obj = parser.parse( /* 매개변수로 Reader 요구 */
					new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
							urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
			// 4. 읽어온 Object를 JSONObject로 캐스팅
			JSONObject jsonFile = (JSONObject) obj;
			// 5. 전체 레시피 재료수를 얻기 위해 객체 얻어오기 (위의 샘플데이터 내용 참조)
			JSONObject rootObj = (JSONObject) jsonFile.get(svcKey);
			// 6. 전체 레시피 재료 수
			long totalCnt = (long) rootObj.get("totalCnt");
			// 7. 레시지 재료 마지막 순번 설정
			endIdx = totalCnt + "";
			// endIdx = 999999+"";
			// 이제 전체 데이터를 얻어올 준비 완료 ★

			// 1. 새로 설정한 endIdx로 데이터 불러오기
			url = new URL(uri);
			// 2. URLConncetion 객체 생성
			urlConnection = url.openConnection();
			// 3. obj에 전체 데이터 담기 (startIdx ~ endIdx)
			obj = parser.parse( /* 매개변수로 Reader 요구 */
					new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
							urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
			// 4. 읽어온 Object를 JSONObject로 캐스팅
			jsonFile = (JSONObject) obj;
			// 5. 전체 레시피 재료 수를 얻기위해 객체 얻어오기
			rootObj = (JSONObject) jsonFile.get(svcKey);
			// 6. result 가져와서 코드값을 비교하여 정상 데이터인지 확인
			JSONArray list = (JSONArray) rootObj.get("row");
			recipeIdList = new ArrayList<>();
			for (Object tempObj : list) { // 배열도 foreach 사용 가능
				JSONObject tempJson = (JSONObject) tempObj;
				tempJson.get("ROW_NUM");
				recipeIdList.add(tempJson.get("RECIPE_ID").toString());
			}
			// 1 4 7 60 110
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return recipeIdList;
	}
	
	public static Map<String, Object> getRecipeNMList(List<String> recipeIdList) {
		String svcKey = "Grid_20150827000000000226_1"; // 레시피 재료 정보 조회 서비스
		String apiKey = "2db3f42b40ee4b4668fba446cdd68069e0eb4247d0c683af0705250e590ad523"; // 개인별 발급.
		String startIdx = "1"; // 레시피 재료 시작 순번
		String endIdx = "999"; // 레시피 재료 종료 순번 - 전체 순번 몰라서 일단 5로
		Map<String, Object> recipeNMList = new HashMap<>();;
		for (String recipeId : recipeIdList) {
			String uri = "http://211.237.50.150:7080/openapi/" + apiKey + "/json/" + svcKey + "/" + startIdx + "/"
					+ endIdx;
			try {
				URL url = new URL(uri);
				// startIdx, endIdx를 몰라도 넣어줘야하도록 url이 설계되어있음
				// 1. URLConnection 객체 생성
				URLConnection urlConnection = url.openConnection();
				// 2. Parsing을 위한 Parser객체 생성
				JSONParser parser = new JSONParser();
				// 3. JSON 문자열 데이터를 읽어오려고 보조스트림 InputStreamReader 활용
				Object obj = parser.parse( /* 매개변수로 Reader 요구 */
						new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
								urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
				// 4. 읽어온 Object를 JSONObject로 캐스팅
				JSONObject jsonFile = (JSONObject) obj;
				// 5. 전체 레시피 재료수를 얻기 위해 객체 얻어오기 (위의 샘플데이터 내용 참조)
				JSONObject rootObj = (JSONObject) jsonFile.get(svcKey);
				// 6. 전체 레시피 재료 수
				long totalCnt = (long) rootObj.get("totalCnt");
				// 7. 레시지 재료 마지막 순번 설정
				endIdx = totalCnt + "";
				// endIdx = 999999+"";
				// 이제 전체 데이터를 얻어올 준비 완료 ★

				// 1. 새로 설정한 endIdx로 데이터 불러오기
				url = new URL(uri);
				// 2. URLConncetion 객체 생성
				urlConnection = url.openConnection();
				// 3. obj에 전체 데이터 담기 (startIdx ~ endIdx)
				obj = parser.parse( /* 매개변수로 Reader 요구 */
						new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
								urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
				// 4. 읽어온 Object를 JSONObject로 캐스팅
				jsonFile = (JSONObject) obj;
				// 5. 전체 레시피 재료 수를 얻기위해 객체 얻어오기
				rootObj = (JSONObject) jsonFile.get(svcKey);
				// 6. result 가져와서 코드값을 비교하여 정상 데이터인지 확인
				JSONArray list = (JSONArray) rootObj.get("row");
				for (Object tempObj : list) { // 배열도 foreach 사용 가능
					JSONObject tempJson = (JSONObject) tempObj;
					tempJson.get("ROW_NUM");
					if(tempJson.get("RECIPE_ID").toString().equals(recipeId)) {
						recipeNMList.put(tempJson.get("RECIPE_ID").toString(), tempJson.get("RECIPE_NM_KO").toString());
						System.out.println(" 레시피ID : " + tempJson.get("RECIPE_ID"));
						System.out.println(" 레시피이름 : " + tempJson.get("RECIPE_NM_KO").toString());
					}
				}
				// 1 4 7 60 110
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			System.out.println("==============");
		}
		System.out.println("map: " + recipeNMList);
		return recipeNMList;
	}
	
	public static List<RecipeVO> getRecipeList(List<String> recipeIdList, Map<String, Object> recipeNMList) {
		String svcKey = "Grid_20150827000000000228_1"; // 레시피 재료 정보 조회 서비스
		String apiKey = "2db3f42b40ee4b4668fba446cdd68069e0eb4247d0c683af0705250e590ad523"; // 개인별 발급.
		String startIdx = "1"; // 레시피 재료 시작 순번
		String endIdx = "5"; // 레시피 재료 종료 순번 - 전체 순번 몰라서 일단 5로
		List<RecipeVO> recipeList = new ArrayList<>();
		for (String recipeId : recipeIdList) {
			String uri = "http://211.237.50.150:7080/openapi/" + apiKey + "/json/" + svcKey + "/" + startIdx + "/"
					+ endIdx + "?RECIPE_ID=" + recipeId;
			try {
				URL url = new URL(uri);
				// startIdx, endIdx를 몰라도 넣어줘야하도록 url이 설계되어있음
				// 1. URLConnection 객체 생성
				URLConnection urlConnection = url.openConnection();
				// 2. Parsing을 위한 Parser객체 생성
				JSONParser parser = new JSONParser();
				// 3. JSON 문자열 데이터를 읽어오려고 보조스트림 InputStreamReader 활용
				Object obj = parser.parse( /* 매개변수로 Reader 요구 */
						new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
								urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
				// 4. 읽어온 Object를 JSONObject로 캐스팅
				JSONObject jsonFile = (JSONObject) obj;
				// 5. 전체 레시피 재료수를 얻기 위해 객체 얻어오기 (위의 샘플데이터 내용 참조)
				JSONObject rootObj = (JSONObject) jsonFile.get(svcKey);
				// 6. 전체 레시피 재료 수
				long totalCnt = (long) rootObj.get("totalCnt");
				// 7. 레시지 재료 마지막 순번 설정
				endIdx = totalCnt + "";
				// endIdx = 999999+"";
				// 이제 전체 데이터를 얻어올 준비 완료 ★
				// 1. 새로 설정한 endIdx로 데이터 불러오기
				url = new URL(uri);
				// 2. URLConncetion 객체 생성
				urlConnection = url.openConnection();
				// 3. obj에 전체 데이터 담기 (startIdx ~ endIdx)
				obj = parser.parse( /* 매개변수로 Reader 요구 */
						new InputStreamReader( /* getInputStream으로 얻는 바이트 기반을 문자 기반으로 변환 */
								urlConnection.getInputStream())); /* 바이트기반 스트림 리턴 */
				// 4. 읽어온 Object를 JSONObject로 캐스팅
				jsonFile = (JSONObject) obj;
				// 5. 전체 레시피 재료 수를 얻기위해 객체 얻어오기
				rootObj = (JSONObject) jsonFile.get(svcKey);
				// 6. result 가져와서 코드값을 비교하여 정상 데이터인지 확인
				JSONArray list = (JSONArray) rootObj.get("row");
				RecipeVO recipe = new RecipeVO();
				String cooking_no = "";
				String cooking_dc = "";
				for(int i = 0; i<list.size(); i++) {
					JSONObject tempJson = (JSONObject) list.get(i);
					tempJson.get("ROW_NUM");
					String recipe_id = tempJson.get("RECIPE_ID").toString();
					String recipe_nm = recipeNMList.get(recipe_id).toString();
					cooking_no = tempJson.get("COOKING_NO").toString();
					cooking_dc += cooking_no+", "+ tempJson.get("COOKING_DC").toString()+"/";
					recipe.setRECIPE_ID(recipe_id);
					recipe.setRECIPE_NM_KO(recipe_nm);
					recipe.setCOOKING_DC(cooking_dc);
				}
				
				recipeList.add(recipe);
				// 1 4 7 60 110
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return recipeList;
	}
}
