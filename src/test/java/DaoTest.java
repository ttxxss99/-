import com.salary.app;
import com.salary.dao.PlaceDao;
import com.salary.model.Vo.PlaceListVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = app.class)
public class DaoTest {

    @Autowired
    PlaceDao placeDao;

    @Test
    public void selectByNameTest(){
        PlaceListVo placeListVo = new PlaceListVo();
        placeListVo.seteName("张三");
//        placeListVo.setpName("武汉");
        List<PlaceListVo> placeListVos1 = placeDao.selectAll();
        System.out.println(placeListVos1.toString());
        List<PlaceListVo> placeListVos = placeDao.selectByName(placeListVo);

        System.out.println(placeListVos.toString());
    }
}
