package com.origin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.origin.entity.Pielist6;
import com.origin.entity.Shangpin;
import com.origin.entity.Totalmoneybyrate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShangpinMapper extends BaseMapper<Shangpin> {

    @Select("select sum(ratesum) as totalmoney \n"+
            "from shangpin01 \n"+
            "group by date_format(sdate, '%Y-%m')")
    List<Double> selectbymounth();

    @Select("SELECT sum(ratesum)\n" +
            "FROM shangpin01\n" +
            "GROUP BY stype\n" +
            "ORDER BY stype")
    List<Double> selectbystype();

    @Select("SELECT t1.*,t2.*,t3.*,t4.*,t5.*,t6.*\n" +
            "        FROM\n" +
            "        (select sum(s1.ssum) as totalmoney1,s1.sdate as sdate1,s1.stype as stype1\n" +
            "        from shangpin01 as s1\n" +
            "        WHERE s1.stype=1 AND YEAR(s1.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s1.sdate, '%Y-%m')\n" +
            "        ORDER BY s1.sdate)t1\n" +
            "        LEFT OUTER JOIN\n" +
            "        (select sum(s2.ssum) as totalmoney2,s2.sdate as sdate2,s2.stype as stype2\n" +
            "        FROM shangpin01 as s2\n" +
            "        WHERE s2.stype=2 AND YEAR(s2.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s2.sdate, '%Y-%m')\n" +
            "        ORDER BY s2.sdate)t2\n" +
            "        on DATE_FORMAT(t2.sdate2,'%Y-%m')=DATE_FORMAT(t1.sdate1,'%Y-%m')\n" +
            "        LEFT OUTER JOIN\n" +
            "        (select sum(s3.ssum) as totalmoney3,s3.sdate as sdate3,s3.stype as stype3\n" +
            "        FROM shangpin01 as s3\n" +
            "        WHERE s3.stype=3 AND YEAR(s3.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s3.sdate, '%Y-%m')\n" +
            "        ORDER BY s3.sdate)t3\n" +
            "        on DATE_FORMAT(t3.sdate3,'%Y-%m')=DATE_FORMAT(t1.sdate1,'%Y-%m')\n" +
            "        LEFT OUTER JOIN\n" +
            "        (select sum(s4.ssum) as totalmoney4,s4.sdate as sdate4,s4.stype as stype4\n" +
            "        FROM shangpin01 as s4\n" +
            "        WHERE s4.stype=4 AND YEAR(s4.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s4.sdate, '%Y-%m')\n" +
            "        ORDER BY s4.sdate)t4\n" +
            "        on DATE_FORMAT(t4.sdate4,'%Y-%m')=DATE_FORMAT(t1.sdate1,'%Y-%m')\n" +
            "        LEFT OUTER JOIN\n" +
            "        (select sum(s5.ssum) as totalmoney5,s5.sdate as sdate5,s5.stype as stype5\n" +
            "        FROM shangpin01 as s5\n" +
            "        WHERE s5.stype=5 AND YEAR(s5.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s5.sdate, '%Y-%m')\n" +
            "        ORDER BY s5.sdate)t5\n" +
            "        on DATE_FORMAT(t5.sdate5,'%Y-%m')=DATE_FORMAT(t1.sdate1,'%Y-%m')\n" +
            "        LEFT OUTER JOIN\n" +
            "        (select sum(s6.ssum) as totalmoney6,s6.sdate as sdate6,s6.stype as stype6\n" +
            "        FROM shangpin01 as s6\n" +
            "        WHERE s6.stype=6 AND YEAR(s6.sdate)=YEAR(NOW())\n" +
            "        group by date_format(s6.sdate, '%Y-%m')\n" +
            "        ORDER BY s6.sdate)t6\n" +
            "        on DATE_FORMAT(t6.sdate6,'%Y-%m')=DATE_FORMAT(t1.sdate1,'%Y-%m')")
    List<Totalmoneybyrate> selectbar();

}
