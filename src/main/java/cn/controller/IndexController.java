package cn.controller;

import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.TypePo;
import cn.pojo.vo.SongListVo;
import cn.pojo.vo.SongVo;
import cn.service.impl.RankListServiceImpl;
import cn.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    RankListServiceImpl rankListServiceImpl;
    @Autowired
    TypeServiceImpl typeServiceImpl;

    @RequestMapping("/areaOneSingerList")
    public @ResponseBody
    List<SingerPo> areaOneSingerList() {
        //华语查询热度前六位的选手
        PageBasePo<SingerPo> pageBasePo = rankListServiceImpl.getHotSingerRank(1, 6, 1);
        List<SingerPo> list = pageBasePo.getList();
        return list;
    }

    @RequestMapping("/areaTwoSingerList")
    public @ResponseBody
    List<SingerPo> areaTwoSingerList() {
        //日韩查询热度前六位的选手
        PageBasePo<SingerPo> pageBasePo = rankListServiceImpl.getHotSingerRank(14, 6, 1);
        List<SingerPo> list = pageBasePo.getList();
        return list;
    }


    @RequestMapping("/areaThreeSingerList")
    public @ResponseBody
    List<SingerPo> areaThreeSingerList() {
        //欧美查询热度前六位的选手
        PageBasePo<SingerPo> pageBasePo = rankListServiceImpl.getHotSingerRank(26, 6, 1);
        List<SingerPo> list = pageBasePo.getList();
        return list;
    }

    //热门歌曲排行
    @RequestMapping("/songHotRank")
    public @ResponseBody
    PageBasePo<SongVo> songHotRank() {
        return rankListServiceImpl.getSongHotRank(8, 1);

    }


    //歌单推荐
    @RequestMapping("/getSongListRemote")
    public @ResponseBody
    List<SongListVo> getSongListRemote() {
        PageBasePo<SongListVo> pageBasePo = rankListServiceImpl.getHotSongListRank(9, 1);
        List<SongListVo> list = pageBasePo.getList();
        return list;

    }


    //经典热曲
    @RequestMapping("/getClassiChitSong")
    public @ResponseBody
    List<SongVo> getClassiChitSong() {
        PageBasePo<SongVo> list = rankListServiceImpl.getSongHotRank(7, 1);
        return list.getList();
    }

    //新歌首发 华语
    @RequestMapping("/getNewStareByChinese")
    public @ResponseBody
    List<SongVo> getNewStareByChinese() {
        PageBasePo<SongVo> list = rankListServiceImpl.getNewSongRank("%国语%", 8, 1);
        return list.getList();
    }

    //新歌首发 其他语
    @RequestMapping("/getNewStareNoChinese")
    public @ResponseBody
    List<SongVo> getNewStareNoChinese() {
        PageBasePo<SongVo> list = rankListServiceImpl.getNewSongRankNo("国语", 8, 1);
        return list.getList();
    }

    //分类
    @RequestMapping("/getSongType")
    public @ResponseBody
    List<TypePo> getSongType() {
        List<TypePo> list = typeServiceImpl.getAllTypePo();
        return list.subList(0, 8);
    }

}
