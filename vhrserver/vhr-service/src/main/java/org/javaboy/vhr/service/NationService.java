package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.NationMapper;
import org.javaboy.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author szh
 * @Date 2022/6/25 8:45
 * @PackageName:org.javaboy.vhr.service
 * @ClassName: NationService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.findAllNations();
    }
}
