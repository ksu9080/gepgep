package com.example.gepgep;

public class text2dots {

    // 한글 숫자 자소변환 메소드

    public static char[][] KOREA_disassmble = new char[30][3]; // 자소변환 된 데이터를 저장하기 위한 공간 [0] = 초성 [1] = 중성 [2] = 종성 최대 데이터 수 30개까지

    // 유니코드 분석을 위한 초성 중성 종성 선언
    private final static char[] KOREA_S =
            {
                    'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ',
                    'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
            }; // 19 순서는 유니코드 기준 이하 동문


    private final static char[] KOREA_M =
            {
                    'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ',
                    'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'
            }; // 21
    private final static char[] KOREA_E =
            {
                    0, 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ',
                    'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
            }; // 28


    public static String disassemble_korean(String text) // 문자열을 한글 자소로 분리 (완성) 함수
    {

        int count = 0; // 반복횟수 카운트 변수
        KOREA_disassmble = new char[30][3]; // 배열 정보 초기화

        if (text == null) {
            return null;
        } // 해당 텍스트가 null이라면 아무것도 반환하지 않음

        if (text.length() == 0) {
            return "";
        } // 해당 텍스트의 길이가 0이라면 공백을 반환

        // 접속사 전처리를 위한 문자열 변환

        text = text.replace("그래서", "Ⅰ"); // 쓰지않는 로마숫자로 치환 이하 동문
        text = text.replace("그러나", "Ⅱ");
        text = text.replace("그러면", "Ⅲ");
        text = text.replace("그러므로", "Ⅳ");
        text = text.replace("그런데", "Ⅴ");
        text = text.replace("그리고", "Ⅵ");
        text = text.replace("그리하여", "Ⅶ");

        // 한글자당 최대 3글자가 될 수 있다. ==> 초성, 중성, 종성
        StringBuilder rv = new StringBuilder(text.length() * 3);

        // 확장 for문 foreach와 유사하다.
        // ToCharArray == 문자열을 char형 배열로 바꾼다.
        // hello => [h] [e] [l] [l] [o]
        // 송명기 => [송] [명] [기]
        for (char cho : text.toCharArray()) // ex 송명기라면 송,명,기에 대해 분리한다. 배열이나 컬렉션의 값이 차례대로 대입되어 반복 cho는 송, 명, 기가 되어 반복문을 진행한다.
        {


            if (cho >= '가' && cho <= '힣') // 한글 유니코드 시작과 끝 즉 한글의 경우에만 이 시퀀스에 따라 분해된다.
            {
                // 한글의 시작부분을 구함
                int ce = cho - '가'; // ''는 해당 문자에 해당하는 integer형으로 변환 한글의 경우는 유니코드로 변환

                // 초성을 구함
                rv.append(KOREA_S[ce / (588)]); // 21 * 28 초성을 rv 문자열에 추가
                KOREA_disassmble[count][0] = KOREA_S[ce / (588)]; // 초성을 전송 데이터 count 행  0열에 저장

                // 중성을 구함
                rv.append(KOREA_M[(ce = ce % (588)) / 28]); // 21 * 28 중성을 rv 문자열에 추가
                KOREA_disassmble[count][1] = KOREA_M[(ce = ce % (588)) / 28]; // 중성을 전송 데이터 count 행 1열에 저장
                // 종성을 구함
                if ((ce = ce % 28) != 0) //종성이 없는 경우가 아니라면
                {
                    rv.append(KOREA_E[ce]); // 종성을 rv 문자열에 추가
                    KOREA_disassmble[count][2] = KOREA_E[ce]; // 종성을 전송 데이터 count 행 2열에 저장
                }
            } else if (cho == ' ') // 띄어쓰기의 경우
            {
                rv.append(' ');
                KOREA_disassmble[count][0] = ' '; // 공백이라면 count 행 0열에 저장
            } else // 한글 공백이 아닌 경우
            {
                rv.append(cho); // 이외의 값은 그냥 반환한다.
                KOREA_disassmble[count][0] = cho; // 이외의 값은 그냥 넣는다.
            }
            count++; // 회차 증가
        }
        return rv.toString(); // rv라는 문자열을 반환한다.
    }

    // 자소 점자 변환 메소드 데이터 베이스

    private final static char[] KOREA_dots_array_simbol_char = // 할지 말지 미정
            {
                    '.', '?', '!', ','
            };

    private final static int[] KOREA_dots_array_simbol_binary = // 할지 말지 미정
            {       //   0           1           2           3          4           5           6
                    0b00010110, 0b00100000, 0b00110000, 0b00100100, 0b00100110, 0b00100010, 0b00110100, // 0 ~ 6
                    0b00110110, 0b00110010, 0b00010100 // 7 ~ 9
                    //   7           8           9
            };


    private final static char[] KOREA_dots_array_num_char =
            {
                    '0', '1', '2', '3', '4', '5', '6', // 0 ~ 6
                    '7', '8', '9'
            };

    private final static int[] KOREA_dots_array_num_binary =
            {       //   0           1           2           3          4           5           6
                    0b00010110, 0b00100000, 0b00110000, 0b00100100, 0b00100110, 0b00100010, 0b00110100, // 0 ~ 6
                    0b00110110, 0b00110010, 0b00010100 // 7 ~ 9
                    //   7           8           9
            };

    private final static char[] KOREA_dots_array_c_char =
            {

                    'Ⅰ', 'Ⅱ', 'Ⅲ', 'Ⅳ', 'Ⅴ', 'Ⅵ', 'Ⅶ', // 0 ~ 6
            };

    private final static int[] KOREA_dots_array_c_binary =
            {       //  그래서1    그래서2      그러나1     그러나2      그러면1      그러면2    그러므로1
                    0b00100000, 0b00011100, 0b00100000, 0b00100100, 0b00100000, 0b00010010, 0b00100000, // 0 ~ 6
                    0b00010001, 0b00100000, 0b00101110, 0b00100000, 0b00101001, 0b00100000, 0b00100011, // 7 ~ 15
                    //그러므로2    그런데1      그런데2     그리고1      그리고2     그리하여1   그리하여2
            };

    private final static char[] KOREA_dots_array_s_char =
            {
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', // 0 ~ 6
                    'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ', // 7 ~ 13
                    'ㄲ', 'ㄸ', 'ㅃ', 'ㅆ', 'ㅉ' // 14 ~ 18
            };

    private final static int[] KOREA_dots_array_s_binary =
            {       //   ㄱ         ㄴ           ㄷ          ㄹ          ㅁ          ㅂ          ㅅ
                    0b00000100, 0b00100100, 0b00010100, 0b00000010, 0b00100010, 0b00000110, 0b00000100, //0
                    0b00110110, 0b00000101, 0b00000011, 0b00110100, 0b00110010, 0b00100110, 0b00010110, //7 기본자음 14개
                    //   ㅇ          ㅈ         ㅊ           ㅋ          ㅌ          ㅍ          ㅎ
                    //   ㄲ         ㄲ2         ㄸ           ㄸ1         ㅃ          ㅃ1         ㅆ
                    0b00000001, 0b00000100, 0b00000001, 0b00010100, 0b00000001, 0b00000110, 0b00000001, //14 추가자음 데이터 10개
                    0b00000001, 0b00000001, 0b00000101 //23
                    //   ㅆ1         ㅉ         ㅉ1
            };

    private final static char[] KOREA_dots_array_m_char =
            {
                    'ㅏ', 'ㅑ', 'ㅓ', 'ㅕ', 'ㅗ', 'ㅛ', 'ㅜ',
                    'ㅠ', 'ㅡ', 'ㅣ', 'ㅐ', 'ㅔ', 'ㅖ', 'ㅘ',
                    'ㅚ', 'ㅝ', 'ㅢ', 'ㅒ', 'ㅙ', 'ㅞ', 'ㅟ',
            };

    private final static int[] KOREA_dots_array_m_binary =
            {
                    //   ㅏ         ㅑ           ㅓ          ㅕ          ㅗ          ㅛ          ㅜ
                    0b00110001, 0b00001110, 0b00011100, 0b00100011, 0b00101001, 0b00001101, 0b00101100, //1
                    0b00100101, 0b00010101, 0b00101010, 0b00111010, 0b00101110, 0b00001100, 0b00111001,
                    //   ㅠ         ㅡ           ㅣ          ㅐ          ㅔ          ㅖ          ㅘ
                    //   ㅚ         ㅝ           ㅢ          ㅒ1         ㅒ2         ㅙ1         ㅙ2
                    0b00101111, 0b00111100, 0b00010111, 0b00001110, 0b00111010, 0b00111001, 0b00111010,
                    0b00111100, 0b00111010, 0b00101100, 0b00111010
                    //   ㅞ1        ㅞ2          ㅟ1         ㅟ2

            };

    private final static char[] KOREA_dots_array_e_char =
            {
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅅ', // 0 ~ 6
                    'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ', // 7 ~ 13
                    'ㅆ', 'ㄲ', 'ㄳ', 'ㄵ', 'ㄶ', 'ㄺ', 'ㄻ', // 14 ~ 20 15 부터 1 : 2 데이터
                    'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅄ'       // 21 ~ 25
            };

    private final static int[] KOREA_dots_array_e_binary =
            {
                    //   ㄱ         ㄴ           ㄷ          ㄹ          ㅁ          ㅂ          ㅅ
                    0b00100000, 0b00010010, 0b00001010, 0b00010000, 0b00010001, 0b00110000, 0b00001000, // 0 ~ 6
                    0b00011011, 0b00101000, 0b00011000, 0b00011010, 0b00011001, 0b00010011, 0b00001011, // 7 ~ 13
                    //   ㅇ          ㅈ         ㅊ           ㅋ          ㅌ          ㅍ          ㅎ
                    //   ㅆ         ㄲ1         ㄲ2          ㄳ1         ㄳ2         ㄵ1         ㄵ2
                    0b00001100, 0b00100000, 0b00100000, 0b00100000, 0b00001000, 0b00010010, 0b00101000, // 14 ~ 20
                    0b00010010, 0b00001011, 0b00010000, 0b00100000, 0b00001000, 0b00010001, 0b00010000, // 21 ~ 27
                    //   ㄶ1        ㄶ2         ㄺ1          ㄺ2         ㄻ1         ㄻ2         ㄼ1
                    //   ㄼ2        ㄽ1         ㄽ2          ㄾ1         ㄾ2         ㄿ1         ㄿ2
                    0b00110000, 0b00010000, 0b00001000, 0b00010000, 0b00011001, 0b00010000, 0b00010011, // 28 ~ 34
                    0b00010000, 0b00001011, 0b00110000, 0b00001000                                      // 35 ~ 38
                    //  ㅀ1         ㅀ2         ㅄ1          ㅄ2
            };


    private final static int[] KOREA_dots_array_exception_binary =
            {
                    // 붙임표0       가1         사2          얼3         열4        은5          을6
                    0b00001001, 0b00110101, 0b00111000, 0b00011110, 0b00110011, 0b00101011, 0b00011101, // 0 ~ 6
                    0b00011111, 0b00101111, 0b00110111, 0b00111011, 0b00111101, 0b00111110, 0b00100111, // 7 ~ 13
                    //  언7          외8        영9          온10        울11        인12         억13
                    //  옥14         옹15       운16         연17        것18        것19
                    0b00101101, 0b00111111, 0b00110110, 0b00100001, 0b00000111, 0b00011100              // 14 ~ 19
            };

    public static int[] dots_datas = new int[90]; // 자소변환 된 데이터를 저장하기 위한 30 * 3 공간 초성 중성 = 최대 데이터 수 90개까지
    public static char[] dots_data_for_Atmega = new char[90];

    public static void korea_disassemble2dots_data(char[][] disassemble) // 약자 포함 번역 함수
    {
        // 초기 변수 선언
        int count = 0; // 데이터 채우기 카운트 변수
        int num = 0;
        int chain = 0;
        int basic = 1;

        dots_datas = new int[90];
        dots_data_for_Atmega = new char[90];

        // 데이터 양을 파악하기 위한 조건 획득
        for (int i = 0; i < 90; i++) // 데이터는 0 ~ 29까지 있으므로
        {
            chain = 0;
            basic = 1;
            // 전부 비어있다면 i-1 번째까지만 데이터 변환을 실시한다. // 여기서 말하는 0값은 비어있다는 의미이다. 숫자 0 혹은 스페이스 빈칸과는 다른 의미이다.
            if (disassemble[i][0] == 0 && disassemble[i][1] == 0 && disassemble[i][2] == 0) {
                break; // 데이터 변환을 종료
            }

            if ((disassemble[i][0] == 'ㄱ' || disassemble[i][0] == 'ㄲ' || disassemble[i][0] == 'ㅅ' || disassemble[i][0] == 'ㅆ') && disassemble[i][1] == 'ㅏ') // 가 사 약자 예외
            {
                num = 0; // 한글 입력
                basic = 0;
                if (disassemble[i][0] == 'ㄲ' || disassemble[i][0] == 'ㅆ') {
                    dots_datas[count] = 0b00000001;
                    count++;
                } // 까 싸의 경우 된소리 데이터 추가

                if (disassemble[i][0] == 'ㄱ' || disassemble[i][0] == 'ㄲ') {
                    dots_datas[count] = KOREA_dots_array_exception_binary[1];
                    count++;
                } // 까 가는 가 약자 데이터 추가
                else if (disassemble[i][0] == 'ㅅ' || disassemble[i][0] == 'ㅆ') {
                    dots_datas[count] = KOREA_dots_array_exception_binary[2];
                    count++;
                } // 싸 사는 사 약자 데이터 추가

                if (disassemble[i][2] != 0) // 종성이 있다면 해당 데이터 추가
                {
                    for (int j = 0; j < 26; j++) // 종성 변환 및 저장
                    {
                        if (disassemble[i][2] == KOREA_dots_array_e_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                        {
                            switch (j / 15) {
                                case 0: // 기본 모음
                                    dots_datas[count] = KOREA_dots_array_e_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;

                                default: // 복합 모음
                                    dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 16]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;
                            }
                            break; // 반복 중지rk
                        }
                    }
                }
            } else if ((disassemble[i][0] >= 'ㄴ' && disassemble[i][0] <= 'ㅎ') && (disassemble[i][0] != 'ㅇ' && disassemble[i][0] != 'ㄹ' && disassemble[i][0] != 'ㅊ')
                    && disassemble[i][1] == 'ㅏ')  // 나머지 경우 ㅏ 약자 (아 라 차는 약자가 없다.)
            {
                num = 0; // 한글 입력
                basic = 0;
                if (disassemble[i + 1][0] == 'ㅇ' && disassemble[i + 1][1] != '0') {
                    basic = 1;
                } // 다만 뒤에 모음시작 음절이 있으면 약자 x

                else {
                    for (int j = 0; j < 19; j++) // 초성 탐색 및 저장 (약자가 초성 표기와 같다)
                    {
                        if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                        {
                            switch (j / 14) {
                                case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                    dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;

                                case 1: // 된소리 o
                                    dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;
                            }
                            break; // 반복 중지
                        }
                    }
                    if (disassemble[i][2] != 0) // 종성이 있다면 해당 데이터 추가
                    {
                        for (int j = 0; j < 26; j++) // 종성 변환 및 저장
                        {
                            if (disassemble[i][2] == KOREA_dots_array_e_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                            {
                                switch (j / 15) {
                                    case 0: // 기본 모음
                                        dots_datas[count] = KOREA_dots_array_e_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                        count++;
                                        break;

                                    default: // 복합 모음
                                        dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                        count++;
                                        dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 16]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                        count++;
                                        break;
                                }
                                break; // 반복 중지
                            }
                        }
                    }
                }
            }

            if ((disassemble[i][0] == 'ㄱ' || disassemble[i][0] == 'ㄲ') && disassemble[i][1] == 'ㅓ' && disassemble[i][2] == 'ㅅ') // 것 약자 예외
            {
                num = 0; // 한글 입력
                basic = 0;
                if (disassemble[i][0] == 'ㄲ') {
                    dots_datas[count] = 0b00000001;
                    count++;
                } // 껏 이라면 된소리 부호 추가
                dots_datas[count] = KOREA_dots_array_e_binary[18]; // 것 약자 데이터 1 추가
                count++;
                dots_datas[count] = KOREA_dots_array_e_binary[19]; // 것 약자 데이터 2 추가
                count++;
            }

            if (disassemble[i][0] != 0
                    && (disassemble[i][1] == 'ㅓ' || disassemble[i][1] == 'ㅕ' || disassemble[i][1] == 'ㅗ' || disassemble[i][1] == 'ㅜ' || disassemble[i][1] == 'ㅡ' || disassemble[i][1] == 'ㅣ'))
            // 억 언 얼 / 연 열 영 / 옥 온 옹 / 운 울 / 은 을 / 인 약자
            {
                switch (disassemble[i][1]) {
                    case 'ㅓ':
                        if (disassemble[i][2] >= 'ㄱ' && disassemble[i][2] <= 'ㄳ') // 억 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 추가
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[13]; // 억 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄲ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄳ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];   // ㅅ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] >= 'ㄴ' && disassemble[i][2] <= 'ㄶ') // 언 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[7]; // 언 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄵ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[8];   // ㅈ 추가
                                    count++;
                                    break;

                                case 'ㄶ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] >= 'ㄹ' && disassemble[i][2] <= 'ㅀ') // 얼 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[3]; // 얼 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄺ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄻ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[4];  // ㅁ 추가
                                    count++;
                                    break;

                                case 'ㄼ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[5];  // ㅂ 추가
                                    count++;
                                    break;

                                case 'ㄽ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];  // ㅎ 추가
                                    count++;
                                    break;

                                case 'ㄾ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[11];  // ㅌ 추가
                                    count++;
                                    break;

                                case 'ㄿ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[12];  // ㅍ 추가
                                    count++;
                                    break;

                                case 'ㅀ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        }
                        break;

                    case 'ㅕ':
                        if (disassemble[i][2] >= 'ㄴ' && disassemble[i][2] <= 'ㄶ') // 연 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[17]; // 연 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄵ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[8];   // ㅈ 추가
                                    count++;
                                    break;

                                case 'ㄶ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] >= 'ㄹ' && disassemble[i][2] <= 'ㅀ') // 열 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[4]; // 열 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄺ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄻ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[4];  // ㅁ 추가
                                    count++;
                                    break;

                                case 'ㄼ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[5];  // ㅂ 추가
                                    count++;
                                    break;

                                case 'ㄽ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];  // ㅎ 추가
                                    count++;
                                    break;

                                case 'ㄾ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[11];  // ㅌ 추가
                                    count++;
                                    break;

                                case 'ㄿ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[12];  // ㅍ 추가
                                    count++;
                                    break;

                                case 'ㅀ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] == 'ㅇ') // 영 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 영 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[9]; // 영 약자 데이터 추가
                            count++;
                        }
                        break;

                    case 'ㅗ':
                        if (disassemble[i][2] >= 'ㄱ' && disassemble[i][2] <= 'ㄳ') // 옹 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 추가
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[15]; // 옹 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄲ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄳ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];   // ㅅ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] >= 'ㄴ' && disassemble[i][2] <= 'ㄶ') // 온 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[10]; // 온 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄵ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[8];   // ㅈ 추가
                                    count++;
                                    break;

                                case 'ㄶ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] == 'ㅇ') // 옹 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 영 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[15]; // 옹 약자 데이터 추가
                            count++;
                        }
                        break;

                    case 'ㅜ':
                        if (disassemble[i][2] >= 'ㄴ' && disassemble[i][2] <= 'ㄶ') // 운 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[16]; // 운 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄵ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[8];   // ㅈ 추가
                                    count++;
                                    break;

                                case 'ㄶ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        } else if (disassemble[i][2] >= 'ㄹ' && disassemble[i][2] <= 'ㅀ') // 울 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[11]; // 울 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄺ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄻ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[4];  // ㅁ 추가
                                    count++;
                                    break;

                                case 'ㄼ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[5];  // ㅂ 추가
                                    count++;
                                    break;

                                case 'ㄽ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];  // ㅎ 추가
                                    count++;
                                    break;

                                case 'ㄾ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[11];  // ㅌ 추가
                                    count++;
                                    break;

                                case 'ㄿ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[12];  // ㅍ 추가
                                    count++;
                                    break;

                                case 'ㅀ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        }
                        break;

                    case 'ㅡ':
                        if (disassemble[i][2] >= 'ㄹ' && disassemble[i][2] <= 'ㅀ') // 을 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            // 초성 탐색 및 저장
                            for (int j = 0; j < 19; j++) {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[6]; // 을 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄺ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[0];   // ㄱ 추가
                                    count++;
                                    break;

                                case 'ㄻ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[4];  // ㅁ 추가
                                    count++;
                                    break;

                                case 'ㄼ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[5];  // ㅂ 추가
                                    count++;
                                    break;

                                case 'ㄽ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[6];  // ㅎ 추가
                                    count++;
                                    break;

                                case 'ㄾ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[11];  // ㅌ 추가
                                    count++;
                                    break;

                                case 'ㄿ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[12];  // ㅍ 추가
                                    count++;
                                    break;

                                case 'ㅀ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        }
                        break;

                    case 'ㅣ':
                        if (disassemble[i][2] >= 'ㄴ' && disassemble[i][2] <= 'ㄶ') // 인 약자
                        {
                            num = 0; // 한글 입력
                            basic = 0;
                            for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                            {
                                if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                                {
                                    switch (j / 14) {
                                        case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                            if (disassemble[i][0] == 'ㅇ') {
                                                break;
                                            } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                            dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;

                                        case 1: // 된소리 o
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                            count++;
                                            break;
                                    }
                                    break; // 반복 중지
                                }
                            }
                            // 약자 추가
                            dots_datas[count] = KOREA_dots_array_exception_binary[12]; // 인 약자 데이터 추가
                            count++;
                            // 겹받침
                            switch (disassemble[i][2]) {
                                case 'ㄵ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[8];   // ㅈ 추가
                                    count++;
                                    break;

                                case 'ㄶ':
                                    dots_datas[count] = KOREA_dots_array_e_binary[13];  // ㅎ 추가
                                    count++;
                                    break;
                            }
                        }
                        break;
                }


                // 초성
                // 약자
                // 마지막 겹받침


            }

            if ((disassemble[i][0] == 'ㅅ' || disassemble[i][0] == 'ㅆ' || disassemble[i][0] == 'ㅈ' || disassemble[i][0] == 'ㅉ' || disassemble[i][0] == 'ㅊ')
                    && disassemble[i][1] == 'ㅓ' && disassemble[i][2] == 'ㅇ') // 성 썽 정 쩡 청
            {
                num = 0; // 한글 입력
                basic = 0;
                for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                {
                    if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                    {
                        switch (j / 14) {
                            case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            case 1: // 된소리 o
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                dots_datas[count] = KOREA_dots_array_exception_binary[9]; // 영 약자 데이터 추가
                count++;
            }


            // 예외 2번 단독 자음 데이터 변환 + 스페이스 처리 + 연결사 처리 + 숫자 // 완료
            if (disassemble[i][0] != 0 && disassemble[i][1] == 0 && disassemble[i][2] == 0) // 자음만 덩그러니 단독 초성 (기본변환 메소드 호출) - 이 경우에 단독 모음도 자소변환 과정에서 초음 자리에 저장이 된다. 따라서 조금 변형 필요
            {
                if (disassemble[i][0] >= 'ㄱ' && disassemble[i][0] <= 'ㅎ') // 1. 단독 자음
                {
                    basic = 0;
                    num = 0;

                    dots_datas[count] = 0b00111111; // 단독자모표식 점자 추가
                    count++;
                    for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                    {
                        if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                        {
                            switch (j / 14) {
                                case 0: // 된소리 x 자음 'ㅇ'도 표기 가능
                                    dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;

                                case 1: // 된소리 o
                                    dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;
                            }
                            break; // 반복 중지
                        }
                    }
                } else if (disassemble[i][0] >= 'ㅏ' && disassemble[i][0] <= 'ㅣ') // 2. 단독 모음
                {
                    basic = 0;
                    num = 0;

                    dots_datas[count] = 0b00111111; // 단독자모표식 점자 추가
                    count++;
                    for (int j = 0; j < 21; j++) // 중성 탐색 및 저장
                    {
                        if (disassemble[i][0] == KOREA_dots_array_m_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                        {
                            switch (j / 17) {
                                case 0: // 기본 모음
                                    dots_datas[count] = KOREA_dots_array_m_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;

                                case 1: // 복합 모음
                                    dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 17]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 18]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                    count++;
                                    break;
                            }
                            break; // 반복 중지
                        }
                    }
                } else if (disassemble[i][0] == ' ') // 3. 스페이스 처리
                {
                    num = 0; // 한글 입력
                    basic = 0;
                    dots_datas[count] = 0b10000000; // 그 순번에 해당하는 2진데이터를 넣는다.
                    count++;
                } else if (disassemble[i][0] >= 'Ⅰ' && disassemble[i][0] <= 'Ⅶ') // 연결사 처리
                {
                    basic = 0;
                    num = 0;

                    for (int j = 0; j < 7; j++) {
                        if (disassemble[i][0] == KOREA_dots_array_c_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                        {
                            dots_datas[count] = KOREA_dots_array_c_binary[(j % 7) * 2]; // 그 순번에 해당하는 2진데이터를 넣는다.
                            count++;
                            dots_datas[count] = KOREA_dots_array_c_binary[(j % 7) * 2 + 1]; // 그 순번에 해당하는 2진데이터를 넣는다.
                            count++;
                            break; // 반복 중지
                        }
                    }
                } else if (disassemble[i][0] >= '0' && disassemble[i][0] <= '9') // 숫자 처리
                {
                    basic = 0;
                    for (int j = 0; j < 9; j++) // 중성 탐색 및 저장
                    {
                        if (disassemble[i][0] == KOREA_dots_array_num_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                        {
                            if (num == 0) {
                                dots_datas[count] = 0b00001111; // 숫자가 처음 나온 것이라면 수표 추가
                                num = 1; // 숫자 입력 중
                                count++;
                            }
                            dots_datas[count] = KOREA_dots_array_num_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                            count++;
                            break; // 반복 중지
                        }
                    }
                }
            }

            if (basic == 1) // 기본 문법 변환
            {
                num = 0;

                if (disassemble[i][0] != 0 && disassemble[i][1] != 0 && disassemble[i][2] == 0) // 예외 3번 모음 연쇄
                {
                    num = 0;

                    if (disassemble[i + 1][0] == 'ㅇ' && disassemble[i + 1][1] == 'ㅖ') // 지금 음절이 모음으로 끝나고 다음 음절이 예인 경우
                    {
                        chain = 1;
                    } else if ((disassemble[i][1] == 'ㅑ' || disassemble[i][1] == 'ㅘ' || disassemble[i][1] == 'ㅜ' || disassemble[i][1] == 'ㅝ') && (disassemble[i + 1][0] == 'ㅇ' && disassemble[i + 1][1] == 'ㅐ')) // ㅑㅘㅜㅝ 다음에 ㅐ가 오는 경우
                    {
                        chain = 1;
                    }
                }

                for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                {
                    if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                    {
                        switch (j / 14) {
                            case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                if (disassemble[i][0] == 'ㅇ') {
                                    break;
                                } // 만약 초성이 'ㅇ'이라면 데이터 저장 x

                                dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            case 1: // 된소리 o
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                for (int j = 0; j < 21; j++) // 중성 탐색 및 저장
                {
                    if (disassemble[i][1] == KOREA_dots_array_m_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                    {
                        switch (j / 17) {
                            case 0: // 기본 모음
                                dots_datas[count] = KOREA_dots_array_m_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            case 1: // 복합 모음
                                dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 17]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 18]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                for (int j = 0; j < 26; j++) // 종성 변환 및 저장
                {
                    if (disassemble[i][2] == KOREA_dots_array_e_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                    {
                        switch (j / 15) {
                            case 0: // 기본 모음
                                dots_datas[count] = KOREA_dots_array_e_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            default: // 복합 모음
                                dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 16]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                if (chain == 1) // 만약 모음 연쇄라면 마지막에 붙임줄 데이터를 추가한다.
                {
                    dots_datas[count] = 0b00001001; // 붙임표 점자 추가
                    count++;
                }
            }
        }

        // 저장된 데이터 char로 변환


        for (int k = 0; k < 90; k++) {
            dots_data_for_Atmega[k] = (char) (dots_datas[k]);
        }

    }

    public static void korea_disassemble2dots_data_basic(char[][] disassemble) // 전부 풀어쓰기 약자 x 함수
    {
        int count = 0; // 데이터 채우기 카운트 변수
        int basic = 1; // 기본변환 신호
        int num = 0;

        dots_datas = new int[90];
        dots_data_for_Atmega = new char[90];

        for (int i = 0; i < 90; i++) // 데이터는 0 ~ 29까지 있으므로
        {
            basic = 1;

            // 전부 비어있다면 i-1 번째까지만 데이터 변환을 실시한다. // 여기서 말하는 0값은 비어있다는 의미이다. 숫자 0 혹은 스페이스 빈칸과는 다른 의미이다.
            if (disassemble[i][0] == 0 && disassemble[i][1] == 0 && disassemble[i][2] == 0) {
                break; // 데이터 변환을 종료
            }

            if (disassemble[i][0] != 0 && disassemble[i][1] == 0 && disassemble[i][2] == 0) // 자음만 덩그러니 단독 초성 (기본변환 메소드 호출) - 이 경우에 단독 모음도 자소변환 과정에서 초음 자리에 저장이 된다. 따라서 조금 변형 필요
            {
                if (disassemble[i][0] >= '0' && disassemble[i][0] <= '9') // 숫자 처리
                {
                    basic = 0;
                    for (int j = 0; j < 9; j++) // 중성 탐색 및 저장
                    {
                        if (disassemble[i][0] == KOREA_dots_array_num_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                        {
                            if (num == 0) {
                                dots_datas[count] = 0b00001111; // 숫자가 처음 나온 것이라면 수표 추가
                                num = 1; // 숫자 입력 중
                                count++;
                            }
                            dots_datas[count] = KOREA_dots_array_num_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                            count++;
                            break; // 반복 중지
                        }
                    }
                }
            }

            if (basic == 1) // 기본 문법 변환
            {
                num = 0;

                for (int j = 0; j < 19; j++) // 초성 탐색 및 저장
                {
                    if (disassemble[i][0] == KOREA_dots_array_s_char[j]) // 초성이 데이터 j번째 데이터와 일치하다면 해당 데이터에 맞는 방법으로 데이터 저장
                    {
                        switch (j / 14) {
                            case 0: // 된소리 x 자음 'ㅇ' 에 대한 예외 추가해야함
                                if (disassemble[i][0] == 'ㅇ') {
                                    break;
                                } // 만약 초성이 'ㅇ'이라면 데이터 저장 x
                                dots_datas[count] = KOREA_dots_array_s_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            case 1: // 된소리 o
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 14]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_s_binary[(j % 14) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                for (int j = 0; j < 21; j++) // 중성 탐색 및 저장
                {
                    if (disassemble[i][1] == KOREA_dots_array_m_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                    {
                        switch (j / 17) {
                            case 0: // 기본 모음
                                dots_datas[count] = KOREA_dots_array_m_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            case 1: // 복합 모음
                                dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 17]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_m_binary[(j % 17) * 2 + 18]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }
                for (int j = 0; j < 26; j++) // 종성 변환 및 저장
                {
                    if (disassemble[i][2] == KOREA_dots_array_e_char[j]) // 중성이 데이터 j번째 데이터와 일치하다면
                    {
                        switch (j / 15) {
                            case 0: // 기본 모음
                                dots_datas[count] = KOREA_dots_array_e_binary[j]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;

                            default: // 복합 모음
                                dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 15]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                dots_datas[count] = KOREA_dots_array_e_binary[(j % 15) * 2 + 16]; // 그 순번에 해당하는 2진데이터를 넣는다.
                                count++;
                                break;
                        }
                        break; // 반복 중지
                    }
                }

            }
        }

        for (int k = 0; k < 90; k++) {
            dots_data_for_Atmega[k] = (char) (dots_datas[k]);
        }

    }


}
