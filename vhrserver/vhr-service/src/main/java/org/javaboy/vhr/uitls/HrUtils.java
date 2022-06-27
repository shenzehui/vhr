package org.javaboy.vhr.uitls;

import org.javaboy.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author szh
 * @Date 2022/6/23 14:02
 * @PackageName:org.javaboy.vhr.uitls
 * @ClassName: HrUtils
 * @Description: TODO
 * @Version 1.0
 */
public class HrUtils {
    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }
}
