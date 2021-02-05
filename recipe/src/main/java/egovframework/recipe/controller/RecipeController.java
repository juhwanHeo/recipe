package egovframework.recipe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.recipe.utils.RecipeUtils;
import egovframework.recipe.vo.RecipeVO;

@RequestMapping(value = "/cmm")
@Controller
public class RecipeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);
	
	/**
	 * 메인 페이지 조회
	 *
	 * @param request
	 * @param response
	 * @param mav
	 */
	@RequestMapping(value = "/index.do")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		mav.setViewName("main/index");
		return mav;
	}

	/**
	 * 메인 페이지 조회
	 *
	 * @param map
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecipe.ajax", method = RequestMethod.GET)
	public Map<String, Object> getRecipe(@RequestParam Map<String, Object> map, 
								HttpSession session,
								HttpServletRequest request) {
		LOGGER.info("map: " + map);
		List<String> recipeIdList = RecipeUtils.getRecipeIdList(map.get("meterial").toString());
		List<RecipeVO> recipeList = RecipeUtils.getRecipeList(recipeIdList, RecipeUtils.getRecipeNMList(recipeIdList));

		LOGGER.info("recipeList: " + recipeList);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("recipeList", recipeList);
		return res;
	}

}
