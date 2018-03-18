package by.medved.service;

import by.medved.model.Node;

public interface NodeService
{
	/**
	 * Добавление узла в дерево
	 */
	Node putTo(Node node, int weigth);

	/**
	 * Добавление в праую ветку
	 */
	Node putToLeft(Node node, int weigth);

	/**
	 * Создание рандомных узлов в дереве
	 */
	void addNodeToTree(Node root, int count);

	/**
	 * Добавление в праую ветку
	 */
	Node putToRight(Node node, int weigth);

	/**
	 * Проверка вершины на пустоту
	 */
	boolean isNull(Node node);

	/**
	 * Удаление узла из дерева
	 */
	Node deleteNode(Node node, int weigth);

	/**
	 * Создание конового элемента дерева
	 */
	Node createRoot(int weigth);

	/**
	 * Проверка на то, что форма ввода на добавление не пустая
	 */
	Node addNode(Node node, Integer weigth);

	/**
	 * Проверка на то, что форма ввода на удаление не пустая
	 */
	Node delNode(Node node, Integer weigth);
}
