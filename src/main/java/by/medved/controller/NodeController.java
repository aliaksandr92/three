package by.medved.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.medved.model.Node;
import by.medved.repository.NodeRepository;
import by.medved.service.NodeService;
import by.medved.service.impl.NodeToHtmlSerialiserServiceImp;

@Controller
@RequestMapping(value = "", method = RequestMethod.GET)
public class NodeController
{
	private final Logger logger = LoggerFactory.getLogger(NodeController.class);

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NodeRepository nodeRepository;

	@Autowired
	private NodeToHtmlSerialiserServiceImp htmlSerialiserService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTree(@RequestParam("count") int count, ModelMap model)
	{
		logger.info("Создание дерева с рандомными узлами и вывод на экран");
		Node root = nodeService.createRoot((new Random()).nextInt(1000));
		nodeService.addNodeToTree(root, count - 1);

		model.addAttribute("message", htmlSerialiserService.treeString(root));

		return "newTree";
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String changeTree(@RequestParam("addNode") Integer addWeigth, @RequestParam("delNode") Integer delWeigth,
			ModelMap model)
	{
		logger.info("Добавление узла");

		nodeService.addNode(nodeRepository.findFirstByOrderByIdAsc(), addWeigth);
		nodeService.delNode(nodeRepository.findFirstByOrderByIdAsc(), delWeigth);

		model.addAttribute("message",  htmlSerialiserService.treeString(nodeRepository.findFirstByOrderByIdAsc()));

		return "newTree";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showTreeFromDataBase(ModelMap model)
	{
		model.addAttribute("message", htmlSerialiserService.treeString(nodeRepository.findFirstByOrderByIdAsc()));

		return "newTree";
	}
}