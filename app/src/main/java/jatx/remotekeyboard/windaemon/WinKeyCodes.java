package jatx.remotekeyboard.windaemon;

public class WinKeyCodes {
	public static final int KEY_BACKSPACE = 0x08;
	public static final int KEY_TAB = 0x09;
	public static final int KEY_ENTER = 0x0D;
	
	public static final int KEY_SHIFT = 0x10;
	public static final int KEY_CTRL = 0x11;
	public static final int KEY_ALT = 0x12;
	public static final int KEY_CAPS_LOCK = 0x14;
	
	public static final int KEY_ESCAPE = 0x1B;
	
	public static final int KEY_SPACE = 0x20;
	
	public static final int KEY_PG_UP = 0x21;
	public static final int KEY_PG_DN = 0x22;
	public static final int KEY_END = 0x23;
	public static final int KEY_HOME = 0x24;
	
	public static final int KEY_LEFT = 0x25;
	public static final int KEY_UP = 0x26;
	public static final int KEY_RIGHT = 0x27;
	public static final int KEY_DOWN = 0x28;
	
	public static final int KEY_PRINT_SCR = 0x2C;
	
	public static final int KEY_INS = 0x2D;
	public static final int KEY_DEL = 0x2E;
	
	public static final int KEY_0 = 0x30;
	public static final int KEY_1 = 0x31;
	public static final int KEY_2 = 0x32;
	public static final int KEY_3 = 0x33;
	public static final int KEY_4 = 0x34;
	public static final int KEY_5 = 0x35;
	public static final int KEY_6 = 0x36;
	public static final int KEY_7 = 0x37;
	public static final int KEY_8 = 0x38;
	public static final int KEY_9 = 0x39;
	
	public static final int KEY_A = 0x41;
	public static final int KEY_B = 0x42;
	public static final int KEY_C = 0x43;
	public static final int KEY_D = 0x44;
	public static final int KEY_E = 0x45;
	public static final int KEY_F = 0x46;
	public static final int KEY_G = 0x47;
	public static final int KEY_H = 0x48;
	public static final int KEY_I = 0x49;
	public static final int KEY_J = 0x4A;
	public static final int KEY_K = 0x4B;
	public static final int KEY_L = 0x4C;
	public static final int KEY_M = 0x4D;
	public static final int KEY_N = 0x4E;
	public static final int KEY_O = 0x4F;
	public static final int KEY_P = 0x50;
	public static final int KEY_Q = 0x51;
	public static final int KEY_R = 0x52;
	public static final int KEY_S = 0x53;
	public static final int KEY_T = 0x54;
	public static final int KEY_U = 0x55;
	public static final int KEY_V = 0x56;
	public static final int KEY_W = 0x57;
	public static final int KEY_X = 0x58;
	public static final int KEY_Y = 0x59;
	public static final int KEY_Z = 0x5A;
	
	public static final int KEY_L_WIN = 0x5B;
	public static final int KEY_R_WIN = 0x5C;
	
	/*
	 
	0x60 - 0x69 : Numpad 0-9
	0x6A - 0x6F : Multiply, Add, Separator, Subtract, Decimal, Divide
	 
	*/
	
	public static final int KEY_F1 = 0x70;
	public static final int KEY_F2 = 0x71;
	public static final int KEY_F3 = 0x72;
	public static final int KEY_F4 = 0x73;
	public static final int KEY_F5 = 0x74;
	public static final int KEY_F6 = 0x75;
	public static final int KEY_F7 = 0x76;
	public static final int KEY_F8 = 0x77;
	public static final int KEY_F9 = 0x78;
	public static final int KEY_F10 = 0x79;
	public static final int KEY_F11 = 0x7A;
	public static final int KEY_F12 = 0x7B;
	
	/*
	
	0x70 - 0x87 : F1-F24
	0x88 - 0x8F : unassigned
	
	*/
	
	public static final int KEY_NUM_LOCK = 0x90;
	public static final int KEY_SCROLL_LOCK = 0x91;
	
	public static final int KEY_L_SHIFT = 0xA0;
	public static final int KEY_R_SHIFT = 0xA1;
	
	public static final int KEY_L_CTRL = 0xA2;
	public static final int KEY_R_CTRL = 0xA3;
	
	public static final int KEY_VOLUME_MUTE = 0xAD;
	public static final int KEY_VOLUME_DOWN = 0xAE;
	public static final int KEY_VOLUME_UP = 0xAF;
	
	public static final int KEY_OEM_1 = 0xBA; // ;:   жЖ
	
	public static final int KEY_OEM_PLUS = 0xBB; // +
	public static final int KEY_OEM_COMMA = 0xBC; // ,
	public static final int KEY_OEM_MINUS = 0xBD; // -
	public static final int KEY_OEM_PERIOD = 0xBE; // .
	
	public static final int KEY_OEM_2 = 0xBF; // /?   .,
	public static final int KEY_OEM_3 = 0xC0; // `~   ёЁ
	
	/* 
	
	0xC1 - 0xD7 : Reserved
	0xD8 - 0xDA : Unassigned
	
	*/
	
	public static final int KEY_OEM_4 = 0xDB; // [{   хХ
	public static final int KEY_OEM_5 = 0xDC; // \|  \/
	public static final int KEY_OEM_6 = 0xDD; // ]}  ъЪ
	public static final int KEY_OEM_7 = 0xDE; // '"  эЭ
	public static final int KEY_OEM_8 = 0xDF; // various
	
	/*
	
	0xE0 - 0xFE : Misc Fucking Things
	 
	*/
	
	public static final int PSEUDO_KEY_EN_RU = 0x00010037;
	public static final int PSEUDO_KEY_EXIT = 0x00010058;
	
	public static final int PSEUDO_KEY_MASK = 0x00010000;
}
