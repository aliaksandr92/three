package by.medved.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.medved.model.Node;
import by.medved.repository.NodeRepository;
import by.medved.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService
{

	@Autowired
	private NodeRepository nodeRepository;
	
	
	/**
	 * Мапа для добавления узлов в дерево и сохранения их в базу
	 */
	private final Map<Integer, BiFunction<Node, Integer, Node>> ADD_PROCESS = new HashMap<>();
	
	{
		ADD_PROCESS.put(1, (Node node, Integer valueOfWeigth) ->
		{
			if (node.left == null)
			{
				node.left = putTo(node.left, valueOfWeigth);
				nodeRepository.saveAndFlush(node);
				return node.left;
			}
			return putToLeft(node, valueOfWeigth);
		});

		ADD_PROCESS.put(0, (Node node, Integer valueOfWeigth) ->
		{
			return null;
		});

		ADD_PROCESS.put(-1, (Node node, Integer valueOfWeigth) ->
		{
			if (node.rigth == null)
			{
				node.rigth = putTo(node.rigth, valueOfWeigth);
				nodeRepository.saveAndFlush(node);
				return node.rigth;
			}
			return putToRight(node, valueOfWeigth);
		});
	}


	/**
	 * Мапа для удаления узлов из дерева и базы
	 */
	private final Map<Integer, BiFunction<Node, Integer, Node>> DELETE_PROCESS = new HashMap<>();

	{
		DELETE_PROCESS.put(1, (Node node, Integer valueOfWeigth) ->
		{
			if (node.left.weigth == valueOfWeigth)
			{
				nodeRepository.delete(node.left);
				node.left = null;
				return node;
			}
			return deleteNode(node.left, valueOfWeigth);
		});

		DELETE_PROCESS.put(0, (Node node, Integer valueOfWeigth) ->
		{
			return node;
		});

		DELETE_PROCESS.put(-1, (Node node, Integer valueOfWeigth) ->
		{
			if (node.rigth.weigth == valueOfWeigth)
			{
				nodeRepository.delete(node.rigth);
				node.rigth = null;
				return node;
			}
			return deleteNode(node.rigth, valueOfWeigth);
		});
	}

	@Override
	public Node putTo(Node node, int weigth)
	{
		if (node == null)
		{
			return create(weigth);
		}
		return ADD_PROCESS.get(Integer.compare(node.weigth, weigth)).apply(node, weigth);
	}

	private Node create(int weigth)
	{
		return nodeRepository.saveAndFlush(new Node(weigth));
	}

	@Override
	public Node deleteNode(Node node, int weigth)
	{
		return DELETE_PROCESS.get(Integer.compare(node.weigth, weigth)).apply(node, weigth);
	}

	@Override
	public Node putToLeft(Node node, int weigth)
	{
		return isNull(node.left) ? node.left = new Node(weigth) : putTo(node.left, weigth);
	}

	@Override
	public Node putToRight(Node node, int weigth)
	{
		return isNull(node.rigth) ? node.rigth = new Node(weigth) : putTo(node.rigth, weigth);
	}

	@Override
	public void addNodeToTree(Node root, int count)
	{
		for (int countNode = 0; countNode < count; countNode++)
		{
			putTo(root, (new Random()).nextInt(1000));
		}
	}

	@Override
	public boolean isNull(Node node)
	{
		return node == null;
	}

	@Override
	public Node delNode(Node node, Integer weigth)
	{
		return weigth == null ? null : deleteNode(node, weigth);
	}
	
	@Override
	public Node addNode(Node node, Integer weigth)
	{
		return weigth == null ? null : putTo(node, weigth);
	}

	@Override
	public Node createRoot(int weigth)
	{
			nodeRepository.deleteAll();
		return create(weigth);
	}

}
