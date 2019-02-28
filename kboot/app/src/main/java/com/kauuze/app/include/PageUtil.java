package com.kauuze.app.include;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-28 20:09
 */
public class PageUtil {

    /**
     * @param page
     * @param size
     * @param sortName
     * @param isFromBig
     * @return
     */
    public static Pageable getPageable(int page, int size, String sortName, boolean isFromBig){
        Sort sort = null;
        if(isFromBig){
            sort = new Sort(Sort.Direction.DESC,sortName);
        }else{
            sort =new Sort(Sort.Direction.ASC,sortName);
        }
        return PageRequest.of(page - 1,size,sort);
    }

    /**
     * 多字段联合排序
     *
     * List< Order> orders=new ArrayList< Order>();
     * orders.add( new Order(Direction. ASC, "c"));
     * orders.add( new Order(Direction. DESC, "d"));
     * @param page
     * @param size
     * @param orders
     * @return
     */
    public static Pageable getPageable(int page, int size, List<Sort.Order> orders){
        return new PageRequest(page, size, new Sort(orders));
    }

    /**
     * 按id从大到小排序(也就是插入时间顺序),每页显示20条
     * @param page
     * @return
     */
    public static Pageable getPageableDefault(int page){
        return PageRequest.of(page - 1,20,new Sort(Sort.Direction.DESC,"id"));
    }

    /**
     * 按id从小到大排序(也就是插入时间顺序),每页显示20条
     * @param page
     * @return
     */
    public static Pageable getPageableDefaultFromSmall(int page){
        return PageRequest.of(page - 1,20,new Sort(Sort.Direction.ASC,"id"));
    }

    /**
     * 获取排序Sort,用于不需要分页
     * @param fromBig
     * @param field
     * @return
     */
    public static Sort getSort(boolean fromBig,String field){
        if(fromBig){
            return new Sort(Sort.Direction.DESC,field);
        }else{
            return new Sort(Sort.Direction.ASC,field);
        }
    }

}
