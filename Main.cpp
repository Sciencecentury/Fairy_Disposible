#include <stdio.h>
#include <DxLib.h>

int DxInit() {

	// 画面の解像度
	SetGraphMode(1280, 720, 32, 60);

	ChangeWindowMode(true);

	// DXライブラリ初期化
	if (DxLib_Init() == -1) {
		return -1;
	}
	SetDrawScreen(DX_SCREEN_BACK);

	return 0;
}

int Key[256]; // キーが押されているフレーム数を格納する

// キーの入力状態を更新する
int gpUpdateKey() {
	char tmpKey[256]; // 現在のキーの入力状態を格納する
	GetHitKeyStateAll(tmpKey); // 全てのキーの入力状態を得る
	for (int i = 0; i < 256; i++) {
		if (tmpKey[i] != 0) { // i番のキーコードに対応するキーが押されていたら
			Key[i]++;     // 加算
		}
		else {              // 押されていなければ
			Key[i] = 0;   // 0にする
		}
	}
	return 0;
}

/*void LoadImg(int *img) {
	int *img[24];
	LoadDivGraph("img/pl00.png",24,8,3,31,48,img);
}*/

class Fps {
	int mStartTime;         //測定開始時刻
	int mCount;             //カウンタ
	float mFps;             //fps
	static const int N = 60;//平均を取るサンプル数
	static const int FPS = 60;	//設定したFPS

public:
	Fps() {
		mStartTime = 0;
		mCount = 0;
		mFps = 0;
	}

	bool Update() {
		if (mCount == 0) { //1フレーム目なら時刻を記憶
			mStartTime = GetNowCount();
		}
		if (mCount == N) { //60フレーム目なら平均を計算する
			int t = GetNowCount();
			mFps = 1000.f / ((t - mStartTime) / (float)N);
			mCount = 0;
			mStartTime = t;
		}
		mCount++;
		return true;
	}

	void Draw() {
		DrawFormatString(0, 0, GetColor(255, 255, 255), "%.1f", mFps);
	}

	void Wait() {
		int tookTime = GetNowCount() - mStartTime;	//かかった時間
		int waitTime = mCount * 1000 / FPS - tookTime;	//待つべき時間
		if (waitTime > 0) {
			Sleep(waitTime);	//待機
		}
	}
};


int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {

	// 初期化関数呼び出し
	DxInit();

	Fps fps;


	int x = 0,
		y = 0,
		i = 0,
		flg = 0,
		flg_Left = 8,
		flg_Right = 16,
		x_direction = 0,
		ix = 0,
		iy = 0,
		result = 0;
	int img[23];

	LoadDivGraph("img/pl00.png", 24, 8, 3, 32, 48, img);


	while (ProcessMessage() == 0 && ClearDrawScreen() == 0 && CheckHitKey(KEY_INPUT_ESCAPE) == 0) {
		fps.Update();	//更新
		fps.Draw();		//描画		
		ScreenFlip();
		fps.Wait();		//待機

	}

	while (ScreenFlip() == 0 && ProcessMessage() == 0 && ClearDrawScreen() == 0 && gpUpdateKey() == 0) {
		// 画面に描かれているものをすべて消す
		ClearDrawScreen();
	

		// 上下左右のキー入力に対応して x, y の座標値を変更する
		if (Key[KEY_INPUT_LEFT] == 1) {
			if (x_direction > 1) {
				x_direction = 1;
			}
			--x_direction;
			x -= 10;
			flg = 0;
	
		}else if (Key[KEY_INPUT_LEFT] > 1) {
			--x_direction;
			x -= 10;
			
		}
		if (Key[KEY_INPUT_RIGHT] == 1) {
			if (x_direction < 1) {
				x_direction = 1;
			}
			++x_direction;
			x += 10;
			flg = 0;
		}
		else if (Key[KEY_INPUT_RIGHT] > 1) {
			++x_direction;
			x += 10;
			
		} 
		if (Key[KEY_INPUT_UP] == 1) {
			y -= 10;
		}else if (Key[KEY_INPUT_UP] > 1) {
		
			y -= 10;
		}

		if (Key[KEY_INPUT_DOWN] == 1) {
	
			y += 10;
		}
		else if (Key[KEY_INPUT_DOWN] > 1) {
			y += 10;
		}

		if (Key[KEY_INPUT_RIGHT] >= 1 && Key[KEY_INPUT_LEFT] >= 1) {
			if (x_direction > 1) {
				x_direction = 1;
			}
			--x_direction;
			x -= 10;
		}

		if (Key[KEY_INPUT_UP] >= 1 && Key[KEY_INPUT_DOWN] >= 1) {
			y += 10;
		}

		if (Key[KEY_INPUT_LEFT] == 0 && Key[KEY_INPUT_RIGHT] == 0) {
			x_direction = 1;
			flg_Left = 8;
		}
		else if (x_direction != 1) {
			i = 0;
		}
		
		//カウント数から添字を求める。
		ix = i++ % 26 / 3;
		iy = flg++ % 6;

		if (x_direction > 1) {

			if (result != 22 || result != 23) {
				iy = iy + flg_Right;
				result = iy;
			}
			if (result == 22) {
				WaitTimer(10);
				result = 23;
			}
			else if (result == 23) {
				WaitTimer(10);
				result = 22;
			}
				
		}else if (x_direction < 1) {
	
			if (result != 14 || result != 15) {
				iy = iy + flg_Left;
				result = iy;
			}
			if (result == 14) {
				result = 15;
			}
			else if (result == 15) {
				result = 14;
			}
			
		}
		else if (x_direction == 1) {

			result = ix;
		}

		DrawExtendGraph(x, y,x+32*1.25,y+48*1.25, img[result], TRUE);



		// 裏画面の内容を表画面に反映させる
		ScreenFlip();

		// 待たないと処理が早すぎるのでここで２０ミリ秒待つ
		WaitTimer(20);

		// Windows システムからくる情報を処理する
		if (ProcessMessage() == -1) break;

		// ＥＳＣキーが押されたらループから抜ける
		if (CheckHitKey(KEY_INPUT_RETURN) == 1) break;
	}




	// 何かしらのキーを押すと終了
	WaitKey();

	// DXライブラリの終了処理
	DxLib_End();

	return 0;
}