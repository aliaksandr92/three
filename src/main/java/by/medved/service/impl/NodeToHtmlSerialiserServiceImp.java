package by.medved.service.impl;

import org.springframework.stereotype.Service;

import by.medved.model.Node;
import by.medved.service.NodeToHtmlSerialiser;

@Service
public class NodeToHtmlSerialiserServiceImp implements NodeToHtmlSerialiser
{
	private String node = "<ul style='font-family: Wawati SC;'><li>weigth: %d</li>"
			+ "<li>left: %s</li>"
			+ "<li>rigth: %s</li></ul>";

	@Override
	public String treeString(Node root)
	{
		return String.format(node, root.weigth, treeSerialize(root.left), treeSerialize(root.rigth));
	}

	public String treeSerialize(Node tree)
	{
		return tree == null ? "null" : treeString(tree);
	}
}
