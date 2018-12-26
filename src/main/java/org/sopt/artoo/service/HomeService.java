package org.sopt.artoo.service;

import jdk.net.SocketFlow;
import lombok.extern.slf4j.Slf4j;
import org.sopt.artoo.dto.Home;
import org.sopt.artoo.mapper.HomeMapper;
import org.sopt.artoo.model.DefaultRes;
import org.sopt.artoo.utils.ResponseMessage;
import org.sopt.artoo.utils.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class HomeService {

    private final HomeMapper homeMapper;

    /**
     * HomeMapper 생성자 의존성 주입
     */
    public HomeService(HomeMapper homeMapper) {
        this.homeMapper = homeMapper;
    }

    /**
     * 좋아요 순위 5개 작가 보여주기
     * @return DefaultRes
     */
//    @Transactional
    public DefaultRes getAllTodayArtist(){
        final List<Home> todayArtistList = homeMapper.findTodayArtist();
        if(todayArtistList.isEmpty()){
            return DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_ARTIST);
        }
        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_ARTIST, todayArtistList);
    }

}
