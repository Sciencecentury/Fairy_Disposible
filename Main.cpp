#include <stdio.h>
#include <DxLib.h>

int DxInit() {

	// ��ʂ̉𑜓x
	SetGraphMode(1280, 720, 32, 60);

	ChangeWindowMode(true);

	// DX���C�u����������
	if (DxLib_Init() == -1) {
		return -1;
	}
	SetDrawScreen(DX_SCREEN_BACK);

	return 0;
}

int Key[256]; // �L�[��������Ă���t���[�������i�[����

// �L�[�̓��͏�Ԃ��X�V����
int gpUpdateKey() {
	char tmpKey[256]; // ���݂̃L�[�̓��͏�Ԃ��i�[����
	GetHitKeyStateAll(tmpKey); // �S�ẴL�[�̓��͏�Ԃ𓾂�
	for (int i = 0; i < 256; i++) {
		if (tmpKey[i] != 0) { // i�Ԃ̃L�[�R�[�h�ɑΉ�����L�[��������Ă�����
			Key[i]++;     // ���Z
		}
		else {              // ������Ă��Ȃ����
			Key[i] = 0;   // 0�ɂ���
		}
	}
	return 0;
}

/*void LoadImg(int *img) {
	int *img[24];
	LoadDivGraph("img/pl00.png",24,8,3,31,48,img);
}*/

class Fps {
	int mStartTime;         //����J�n����
	int mCount;             //�J�E���^
	float mFps;             //fps
	static const int N = 60;//���ς����T���v����
	static const int FPS = 60;	//�ݒ肵��FPS

public:
	Fps() {
		mStartTime = 0;
		mCount = 0;
		mFps = 0;
	}

	bool Update() {
		if (mCount == 0) { //1�t���[���ڂȂ玞�����L��
			mStartTime = GetNowCount();
		}
		if (mCount == N) { //60�t���[���ڂȂ畽�ς��v�Z����
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
		int tookTime = GetNowCount() - mStartTime;	//������������
		int waitTime = mCount * 1000 / FPS - tookTime;	//�҂ׂ�����
		if (waitTime > 0) {
			Sleep(waitTime);	//�ҋ@
		}
	}
};


int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {

	// �������֐��Ăяo��
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
		fps.Update();	//�X�V
		fps.Draw();		//�`��		
		ScreenFlip();
		fps.Wait();		//�ҋ@

	}

	while (ScreenFlip() == 0 && ProcessMessage() == 0 && ClearDrawScreen() == 0 && gpUpdateKey() == 0) {
		// ��ʂɕ`����Ă�����̂����ׂď���
		ClearDrawScreen();
	

		// �㉺���E�̃L�[���͂ɑΉ����� x, y �̍��W�l��ύX����
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
		
		//�J�E���g������Y�������߂�B
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



		// ����ʂ̓��e��\��ʂɔ��f������
		ScreenFlip();

		// �҂��Ȃ��Ə�������������̂ł����łQ�O�~���b�҂�
		WaitTimer(20);

		// Windows �V�X�e�����炭�������������
		if (ProcessMessage() == -1) break;

		// �d�r�b�L�[�������ꂽ�烋�[�v���甲����
		if (CheckHitKey(KEY_INPUT_RETURN) == 1) break;
	}




	// ��������̃L�[�������ƏI��
	WaitKey();

	// DX���C�u�����̏I������
	DxLib_End();

	return 0;
}