package snippets.algorithms

object ArrayHeap {
  def main(args: Array[String]) = {
    val h = new ArrayHeap()
    h.insert(1)
    println(h.toString)
    h.insert(2)
    println(h.toString)
    h.insert(-5)
    println(h.toString)
    h.insert(10)
    println(h.toString)

  }
}

class ArrayHeap {
  // FIXME: we should support resizing
  // For a node i, children are at 2i + 1 and 2i + 2
  // Parent is (i-1)/2
  var h = Array.fill(20){Int.MaxValue}
  var len = 0

  override def toString() = {
    s"${len}: ${h.slice(0,len).mkString(" ")}"
  }

  // FIXME: Should return Some/None
  def min = h(0)

  def insert(x: Int) = {
    var i = len
    h(i) = x
    // bubble up
    while (h(i) < h((i-1)/2)) {
      swap(i, (i-1)/2)
      i = (i-1)/2
    }
    len = len + 1
  }

  def swap(i: Int, j: Int) = {
    val temp = h(i)
    h(i) = h(j)
    h(j) = temp
  }

}

sealed abstract class Heap {
  def min: Int
}

case class Node(x: Int, l: Heap, r: Heap) extends Heap {
  override def min = x
}

case object End extends Heap {
  override def min = 0 //FIXME: return Some/None
}

object Node {
  def apply(x: Int): Node = Node(x, End, End)
}

