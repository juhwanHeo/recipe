package egovframework.recipe.vo;

/**
 * Recipe 관리하기 위한 VO 클래스
 * 
 * @author 허주환
 * @since 2021.02.04
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      	수정자          	 수정내용
 *  -------    	--------    ---------------------------
 *   2021.02.04  허주환		 최초 생성
 *
 *      </pre>
 */
public class RecipeVO {
	private String RECIPE_ID;
	private String RECIPE_NM_KO;
	private String COOKING_NO;
	private String COOKING_DC;
	private String SUMRY;
	private String NATION_CODE;
	private String TY_CODE;
	private String TY_NM;
	private String COOKING_TIME;
	private String CALORIE;
	private String QNT;
	private String LEVEL_NM;
	private String IRDNT_CODE;
	private String PC_NM;
	
	

	@Override
	public String toString() {
		return "RecipeVO [RECIPE_ID=" + RECIPE_ID + ", RECIPE_NM_KO=" + RECIPE_NM_KO + ", COOKING_NO=" + COOKING_NO
				+ ", COOKING_DC=" + COOKING_DC + "]";
	}

	public String getRECIPE_ID() {
		return RECIPE_ID;
	}

	public void setRECIPE_ID(String rECIPE_ID) {
		RECIPE_ID = rECIPE_ID;
	}

	public String getRECIPE_NM_KO() {
		return RECIPE_NM_KO;
	}

	public void setRECIPE_NM_KO(String rECIPE_NM_KO) {
		RECIPE_NM_KO = rECIPE_NM_KO;
	}

	public String getCOOKING_NO() {
		return COOKING_NO;
	}

	public void setCOOKING_NO(String cOOKING_NO) {
		COOKING_NO = cOOKING_NO;
	}

	public String getCOOKING_DC() {
		return COOKING_DC;
	}

	public void setCOOKING_DC(String cOOKING_DC) {
		COOKING_DC = cOOKING_DC;
	}

	public String getSUMRY() {
		return SUMRY;
	}

	public void setSUMRY(String sUMRY) {
		SUMRY = sUMRY;
	}

	public String getNATION_CODE() {
		return NATION_CODE;
	}

	public void setNATION_CODE(String nATION_CODE) {
		NATION_CODE = nATION_CODE;
	}

	public String getTY_CODE() {
		return TY_CODE;
	}

	public void setTY_CODE(String tY_CODE) {
		TY_CODE = tY_CODE;
	}

	public String getTY_NM() {
		return TY_NM;
	}

	public void setTY_NM(String tY_NM) {
		TY_NM = tY_NM;
	}

	public String getCOOKING_TIME() {
		return COOKING_TIME;
	}

	public void setCOOKING_TIME(String cOOKING_TIME) {
		COOKING_TIME = cOOKING_TIME;
	}

	public String getCALORIE() {
		return CALORIE;
	}

	public void setCALORIE(String cALORIE) {
		CALORIE = cALORIE;
	}

	public String getQNT() {
		return QNT;
	}

	public void setQNT(String qNT) {
		QNT = qNT;
	}

	public String getLEVEL_NM() {
		return LEVEL_NM;
	}

	public void setLEVEL_NM(String lEVEL_NM) {
		LEVEL_NM = lEVEL_NM;
	}

	public String getIRDNT_CODE() {
		return IRDNT_CODE;
	}

	public void setIRDNT_CODE(String iRDNT_CODE) {
		IRDNT_CODE = iRDNT_CODE;
	}

	public String getPC_NM() {
		return PC_NM;
	}

	public void setPC_NM(String pC_NM) {
		PC_NM = pC_NM;
	}

}
