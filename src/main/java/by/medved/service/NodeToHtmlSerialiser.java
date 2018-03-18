package by.medved.service;

import by.medved.model.Node;

public interface NodeToHtmlSerialiser
{
	/**
	 * Преобразование дерева в HTML
	 */
	String treeString(Node root);


	/**
	 * Сериализация дерева
	 */
	String treeSerialize(Node tree);
	
}
