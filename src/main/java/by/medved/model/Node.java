package by.medved.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Создание дерева и добавление в него узлов
 */
@Entity
@Table(name = "node", schema = "public")
public class Node extends AbstractPersistable<Long>
{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Вес элемента дерева
	 */
	@Column(name = "weigth")
	public int weigth;

	@OneToOne()
	@JoinColumn(name = "left_id", nullable = true)
	public Node left;

	@OneToOne()
	@JoinColumn(name = "right_id", nullable = true)
	public Node rigth;

	public Node()
	{

	}

	/**
	 * Конструктор корневого элемента дерева
	 */
	public Node(int weigth)
	{
		this.weigth = weigth;
	}
}
