package jatx.remotekeyboard.windaemon;

import java.util.HashMap;
import java.util.Map;

public class CharMappings {
	public static final Map<Character,int[]> charMapEng;
	public static final Map<Character,int[]> charMapRus;
	
	public static final int LANG_ENG = 0;
	public static final int LANG_RUS = 1;
	
	private static int lang = LANG_ENG;
	
	static {
		charMapEng = new HashMap<Character,int[]>();
		charMapRus = new HashMap<Character,int[]>();
		
		initEngMap();
		initRusMap();
	}
	
	public static void setLang(int lng) {
		lang = lng;
	}
	
	public static int[] getCharMapping(char c) {
		if (lang==LANG_ENG) {
			return charMapEng.get(c);
		} else {
			return charMapRus.get(c);
		}
	}
	
	private static void initEngMap() {
		///// LETTERS:
		
		charMapEng.put('a', new int[]{WinKeyCodes.KEY_A});
		charMapEng.put('A', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_A});
		charMapEng.put('b', new int[]{WinKeyCodes.KEY_B});
		charMapEng.put('B', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_B});
		charMapEng.put('c', new int[]{WinKeyCodes.KEY_C});
		charMapEng.put('C', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_C});
		
		charMapEng.put('d', new int[]{WinKeyCodes.KEY_D});
		charMapEng.put('D', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_D});
		charMapEng.put('e', new int[]{WinKeyCodes.KEY_E});
		charMapEng.put('E', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_E});
		charMapEng.put('f', new int[]{WinKeyCodes.KEY_F});
		charMapEng.put('F', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_F});
		
		charMapEng.put('g', new int[]{WinKeyCodes.KEY_G});
		charMapEng.put('G', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_G});
		charMapEng.put('h', new int[]{WinKeyCodes.KEY_H});
		charMapEng.put('H', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_H});
		charMapEng.put('i', new int[]{WinKeyCodes.KEY_I});
		charMapEng.put('I', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_I});
		
		charMapEng.put('j', new int[]{WinKeyCodes.KEY_J});
		charMapEng.put('J', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_J});
		charMapEng.put('k', new int[]{WinKeyCodes.KEY_K});
		charMapEng.put('K', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_K});
		charMapEng.put('l', new int[]{WinKeyCodes.KEY_L});
		charMapEng.put('L', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_L});
		
		charMapEng.put('m', new int[]{WinKeyCodes.KEY_M});
		charMapEng.put('M', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_M});
		charMapEng.put('n', new int[]{WinKeyCodes.KEY_N});
		charMapEng.put('N', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_N});
		charMapEng.put('o', new int[]{WinKeyCodes.KEY_O});
		charMapEng.put('O', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_O});
		
		charMapEng.put('p', new int[]{WinKeyCodes.KEY_P});
		charMapEng.put('P', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_P});
		charMapEng.put('q', new int[]{WinKeyCodes.KEY_Q});
		charMapEng.put('Q', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Q});
		charMapEng.put('r', new int[]{WinKeyCodes.KEY_R});
		charMapEng.put('R', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_R});

		charMapEng.put('s', new int[]{WinKeyCodes.KEY_S});
		charMapEng.put('S', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_S});
		charMapEng.put('t', new int[]{WinKeyCodes.KEY_T});
		charMapEng.put('T', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_T});
		charMapEng.put('u', new int[]{WinKeyCodes.KEY_U});
		charMapEng.put('U', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_U});
		
		charMapEng.put('v', new int[]{WinKeyCodes.KEY_V});
		charMapEng.put('V', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_V});
		charMapEng.put('w', new int[]{WinKeyCodes.KEY_W});
		charMapEng.put('W', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_W});
		charMapEng.put('x', new int[]{WinKeyCodes.KEY_X});
		charMapEng.put('X', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_X});
		
		charMapEng.put('y', new int[]{WinKeyCodes.KEY_Y});
		charMapEng.put('Y', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Y});
		charMapEng.put('z', new int[]{WinKeyCodes.KEY_Z});
		charMapEng.put('Z', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Z});
		
		///// OEM:
		
		charMapEng.put(';', new int[]{WinKeyCodes.KEY_OEM_1});
		charMapEng.put(':', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_1});
		charMapEng.put('/', new int[]{WinKeyCodes.KEY_OEM_2});
		charMapEng.put('?', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_2});
		charMapEng.put('`', new int[]{WinKeyCodes.KEY_OEM_3});
		charMapEng.put('~', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_3});
		charMapEng.put('[', new int[]{WinKeyCodes.KEY_OEM_4});
		charMapEng.put('{', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_4});
		charMapEng.put('\\', new int[]{WinKeyCodes.KEY_OEM_5});
		charMapEng.put('|', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_5});
		charMapEng.put(']', new int[]{WinKeyCodes.KEY_OEM_6});
		charMapEng.put('}', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_6});
		charMapEng.put('\'', new int[]{WinKeyCodes.KEY_OEM_7});
		charMapEng.put('"', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_7});
		
		/////
		
		charMapEng.put('=', new int[]{WinKeyCodes.KEY_OEM_PLUS});
		charMapEng.put('+', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_PLUS});
		charMapEng.put('-', new int[]{WinKeyCodes.KEY_OEM_MINUS});
		charMapEng.put('_', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_MINUS});
		charMapEng.put(',', new int[]{WinKeyCodes.KEY_OEM_COMMA});
		charMapEng.put('<', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_COMMA});
		charMapEng.put('.', new int[]{WinKeyCodes.KEY_OEM_PERIOD});
		charMapEng.put('>', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_PERIOD});
			
		
		///// NUMBERS:
		
		charMapEng.put('1', new int[]{WinKeyCodes.KEY_1});
		charMapEng.put('!', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_1});
		charMapEng.put('2', new int[]{WinKeyCodes.KEY_2});
		charMapEng.put('@', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_2});
		charMapEng.put('3', new int[]{WinKeyCodes.KEY_3});
		charMapEng.put('#', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_3});
		charMapEng.put('4', new int[]{WinKeyCodes.KEY_4});
		charMapEng.put('$', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_4});
		charMapEng.put('5', new int[]{WinKeyCodes.KEY_5});
		charMapEng.put('%', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_5});
		
		charMapEng.put('6', new int[]{WinKeyCodes.KEY_6});
		charMapEng.put('^', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_6});
		charMapEng.put('7', new int[]{WinKeyCodes.KEY_7});
		charMapEng.put('&', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_7});
		charMapEng.put('8', new int[]{WinKeyCodes.KEY_8});
		charMapEng.put('*', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_8});
		charMapEng.put('9', new int[]{WinKeyCodes.KEY_9});
		charMapEng.put('(', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_9});
		charMapEng.put('0', new int[]{WinKeyCodes.KEY_0});
		charMapEng.put(')', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_0});
		
		/////
		
		charMapEng.put(' ', new int[]{WinKeyCodes.KEY_SPACE});
		charMapEng.put('\t', new int[]{WinKeyCodes.KEY_TAB});
		charMapEng.put('\n', new int[]{WinKeyCodes.KEY_ENTER});
	}
	
	private static void initRusMap() {
		///// LETTERS:
		
		charMapRus.put('а', new int[]{WinKeyCodes.KEY_F});
		charMapRus.put('А', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_F});
		charMapRus.put('б', new int[]{WinKeyCodes.KEY_OEM_COMMA});
		charMapRus.put('Б', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_COMMA});
		charMapRus.put('в', new int[]{WinKeyCodes.KEY_D});
		charMapRus.put('В', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_D});
		
		charMapRus.put('г', new int[]{WinKeyCodes.KEY_U});
		charMapRus.put('Г', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_U});
		charMapRus.put('д', new int[]{WinKeyCodes.KEY_L});
		charMapRus.put('Д', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_L});
		charMapRus.put('е', new int[]{WinKeyCodes.KEY_T});
		charMapRus.put('Е', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_T});
		
		charMapRus.put('ё', new int[]{WinKeyCodes.KEY_OEM_3});
		charMapRus.put('Ё', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_3});
		charMapRus.put('ж', new int[]{WinKeyCodes.KEY_OEM_1});
		charMapRus.put('Ж', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_1});
		charMapRus.put('з', new int[]{WinKeyCodes.KEY_P});
		charMapRus.put('З', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_P});
		
		charMapRus.put('и', new int[]{WinKeyCodes.KEY_B});
		charMapRus.put('И', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_B});
		charMapRus.put('й', new int[]{WinKeyCodes.KEY_Q});
		charMapRus.put('Й', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Q});
		charMapRus.put('к', new int[]{WinKeyCodes.KEY_R});
		charMapRus.put('К', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_R});
		
		charMapRus.put('л', new int[]{WinKeyCodes.KEY_K});
		charMapRus.put('Л', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_K});
		charMapRus.put('м', new int[]{WinKeyCodes.KEY_V});
		charMapRus.put('М', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_V});
		charMapRus.put('н', new int[]{WinKeyCodes.KEY_Y});
		charMapRus.put('Н', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Y});
		
		charMapRus.put('о', new int[]{WinKeyCodes.KEY_J});
		charMapRus.put('О', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_J});
		charMapRus.put('п', new int[]{WinKeyCodes.KEY_G});
		charMapRus.put('П', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_G});
		charMapRus.put('р', new int[]{WinKeyCodes.KEY_H});
		charMapRus.put('Р', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_H});
		
		charMapRus.put('с', new int[]{WinKeyCodes.KEY_C});
		charMapRus.put('С', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_C});
		charMapRus.put('т', new int[]{WinKeyCodes.KEY_N});
		charMapRus.put('Т', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_N});
		charMapRus.put('у', new int[]{WinKeyCodes.KEY_E});
		charMapRus.put('У', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_E});
		
		charMapRus.put('ф', new int[]{WinKeyCodes.KEY_A});
		charMapRus.put('Ф', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_A});
		charMapRus.put('х', new int[]{WinKeyCodes.KEY_OEM_4});
		charMapRus.put('Х', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_4});
		charMapRus.put('ц', new int[]{WinKeyCodes.KEY_W});
		charMapRus.put('Ц', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_W});
		
		charMapRus.put('ч', new int[]{WinKeyCodes.KEY_X});
		charMapRus.put('Ч', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_X});
		charMapRus.put('ш', new int[]{WinKeyCodes.KEY_I});
		charMapRus.put('Ш', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_I});
		charMapRus.put('щ', new int[]{WinKeyCodes.KEY_O});
		charMapRus.put('Щ', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_O});
		
		charMapRus.put('ъ', new int[]{WinKeyCodes.KEY_OEM_6});
		charMapRus.put('Ъ', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_6});
		charMapRus.put('ы', new int[]{WinKeyCodes.KEY_S});
		charMapRus.put('Ы', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_S});
		charMapRus.put('ь', new int[]{WinKeyCodes.KEY_M});
		charMapRus.put('Ь', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_M});
		
		charMapRus.put('э', new int[]{WinKeyCodes.KEY_OEM_7});
		charMapRus.put('Э', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_7});
		charMapRus.put('ю', new int[]{WinKeyCodes.KEY_OEM_PERIOD});
		charMapRus.put('Ю', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_PERIOD});
		charMapRus.put('я', new int[]{WinKeyCodes.KEY_Z});
		charMapRus.put('Я', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_Z});
		
		///// NUMBERS:
		
		charMapRus.put('1', new int[]{WinKeyCodes.KEY_1});
		charMapRus.put('!', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_1});
		charMapRus.put('2', new int[]{WinKeyCodes.KEY_2});
		charMapRus.put('"', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_2});
		charMapRus.put('3', new int[]{WinKeyCodes.KEY_3});
		charMapRus.put('№', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_3});
		charMapRus.put('4', new int[]{WinKeyCodes.KEY_4});
		charMapRus.put(';', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_4});
		charMapRus.put('5', new int[]{WinKeyCodes.KEY_5});
		charMapRus.put('%', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_5});
			
		charMapRus.put('6', new int[]{WinKeyCodes.KEY_6});
		charMapRus.put(':', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_6});
		charMapRus.put('7', new int[]{WinKeyCodes.KEY_7});
		charMapRus.put('?', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_7});
		charMapRus.put('8', new int[]{WinKeyCodes.KEY_8});
		charMapRus.put('*', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_8});
		charMapRus.put('9', new int[]{WinKeyCodes.KEY_9});
		charMapRus.put('(', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_9});
		charMapRus.put('0', new int[]{WinKeyCodes.KEY_0});
		charMapRus.put(')', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_0});
		
		///// OEM:
		
		charMapRus.put('.', new int[]{WinKeyCodes.KEY_OEM_2});
		charMapRus.put(',', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_2});
		charMapRus.put('\\', new int[]{WinKeyCodes.KEY_OEM_5});
		charMapRus.put('/', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_5});
			
		/////
			
		charMapRus.put('=', new int[]{WinKeyCodes.KEY_OEM_PLUS});
		charMapRus.put('+', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_PLUS});
		charMapRus.put('-', new int[]{WinKeyCodes.KEY_OEM_MINUS});
		charMapRus.put('_', new int[]{WinKeyCodes.KEY_SHIFT, WinKeyCodes.KEY_OEM_MINUS});		
			
		/////
		
		charMapRus.put(' ', new int[]{WinKeyCodes.KEY_SPACE});
		charMapRus.put('\t', new int[]{WinKeyCodes.KEY_TAB});
		charMapRus.put('\n', new int[]{WinKeyCodes.KEY_ENTER});
	}
}
