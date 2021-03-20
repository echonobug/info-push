package xyz.ipush.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ipush.web.entity.InfoDefine;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.service.InfoDefineService;
import xyz.ipush.web.util.SecurityContextUtil;

import javax.annotation.Resource;

/**
 * <p>
 * 信息定义  前端控制器
 * </p>
 *
 * @author jwei
 * @date 2021/03/02
 */
@CrossOrigin
@RestController
@RequestMapping("infoDefine")
public class InfoDefineController {
	@Resource
	private InfoDefineService infoDefineService;
	
	@GetMapping("find")
	public void find(String id) {
		infoDefineService.find(id);
	}
	
	@GetMapping("send")
	public void send(String id) {
		infoDefineService.send(id);
	}
	
	@ApiOperation("获取信息定义（附带当前用户关注信息）分页数据")
	@GetMapping("listWithSubInfo")
	public ResponseEntity listWithSubInfo(@RequestParam(name = "page", defaultValue = "1") Integer page,
	                                      @RequestParam(name = "size", defaultValue = "5") Integer size,
	                                      @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		return ResponseEntity.success(
				infoDefineService.listWithSubInfo(page, size, keyword, SecurityContextUtil.getUser().getId())
		);
	}
	
	@ApiOperation("获取信息定义分页数据")
	@PostMapping("list")
	public ResponseEntity list(@RequestBody InfoDefine define,
	                           @RequestParam(name = "page", defaultValue = "1") Integer page,
	                           @RequestParam(name = "size", defaultValue = "5") Integer size) {
		return ResponseEntity.success(
				infoDefineService.list(define, page, size)
		);
	}
	
	@ApiOperation("保存信息定义配置")
	@PostMapping("save")
	public ResponseEntity save(@RequestBody InfoDefine infoDefine) {
		infoDefineService.saveOrUpdate(infoDefine);
		return ResponseEntity.success("保存成功！");
	}
	
	@ApiOperation("删除信息")
	@PostMapping("delete")
	public ResponseEntity delete(String id) {
		infoDefineService.removeById(id);
		return ResponseEntity.success("删除成功！");
	}
	
}
