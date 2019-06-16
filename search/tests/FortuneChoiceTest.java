import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

public class FortuneChoiceTest {

    // モック化するクラスのインスタンスを生成します。
    @Mock
    private UserInfo mockInfo = new UserInfo();

    // モックを注入するクラスのインスタンスを生成します。
    @InjectMocks
    private FortuneChoice fc = new FortuneChoice();

    // this(mockInfo)を初期化します。
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    //ここがテストの中身
    @Test
    public void testMock() {

        // ここでモックを作成しています。
        when(mockInfo.getLank((Integer)anyInt())).thenReturn("一般会員");
        when(mockInfo.getSex((Integer)anyInt())).thenReturn("男");
        when(mockInfo.getName((Integer)anyInt())).thenReturn("mock");

        // テスト対象のクラスを実行します。
        String result = fc.choice();

        String[] resultArray = result.split(",");

        String str1 = resultArray[0];
        String str2 = resultArray[1];
        String str3 = resultArray[2];

        // 戻り値を確認する。
        assertEquals("一般会員の", str1);
        assertEquals("性別は男の", str2);
        assertEquals("mockさんの運勢は、", str3);
    }
}
