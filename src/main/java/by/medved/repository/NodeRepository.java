package by.medved.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.medved.model.Node;

public interface NodeRepository extends JpaRepository<Node, Long>
{
	Node findFirstByOrderByIdAsc();
	Node findByWeigth(int weigth);
}
