//package com.ytz.shop.es.service;

/**
 * @ClassName: EsGoodsServiceImpl
 * @Description: TODO
 * @author: basketBoy
 * @date: 2020/6/7
 * @Version: V1.0
 */
/*
@Service
@Transactional(rollbackOn = Exception.class)
public class EsGoodsServiceImpl implements EsGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private EsGoodsRepository esGoodsRepository;

    @Override
    public int importAll() {
        // 获取数据库 数据
        List<EsGoods> goodsList = goodsRepository.findAllEsGoodsList(null);

        // 添加到ES中
        Iterable<EsGoods> esGoods = esGoodsRepository.saveAll(goodsList);
        int result = 0;
        if (CollUtil.isNotEmpty(esGoods)) {
            result = 1;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        esGoodsRepository.deleteById(id);
    }

    @Override
    public EsGoods create(Long id) {
        List<EsGoods> esGoodsList = goodsRepository.findAllEsGoodsList(id);
        EsGoods esGoods = null;
        if (esGoodsList.size() > 0) {
            EsGoods goods = esGoodsList.get(0);
            esGoods = esGoodsRepository.save(goods);
        }
        return esGoods;
    }

    @Override
    public void batchDelete(List<Long> ids) {
        if (CollUtil.isNotEmpty(ids)) {
            List<EsGoods> list = new ArrayList<>();
            for (Long id : ids) {
                EsGoods esGoods = new EsGoods();
                esGoods.setId(id);
                list.add(esGoods);
            }
            esGoodsRepository.deleteAll(list);
        }
    }

    @Override
    public Page<EsGoods> search(String keyword, Integer pageNum, Integer pageSize) {
        if (StrUtil.isEmpty(keyword)) {
            return null;
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esGoodsRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
*/
