package scala.virtualization.lms
package epfl
package test7

import original.MDArray
import original.Conversions._
import original.Operations._
import original.With

import common._
import test1.Arith

class GameOfLifeOriginal() {
  def testGameOfLife(iter: Int, start: MDArray[Int]): MDArray[Int] = {

    val startTime: Long = System.currentTimeMillis
    var alive: MDArray[Int] = start
    for (i <- List.range(0, iter))
      alive = gameOfLife(alive)

    val finishTime: Long = System.currentTimeMillis
    println(alive)
    println("Time: " + (finishTime-startTime).toString + "ms")
    alive
  }

  def computeIfDead(neigh: Int, alive: Int) = {

    if (alive == 1) {
      if (neigh - alive < 2)
        1 // Rule1: Any live cell with fewer than two live neighbours dies, as if caused by under-population.
      else if (neigh - alive < 4)
        0 // Rule2: Any live cell with two or three live neighbours lives on to the next generation.
      else
        1 // Rule3: Any live cell with more than three live neighbours dies, as if by overcrowding.
    }
    else
      0
  }

  def computeIfReborn(neigh: Int, dead: Int) = {

    if (dead == 1) {
      if (neigh == 3)
        1 // Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
      else
        0
    } else
      0
  }

  def gameOfLife(alive: MDArray[Int]) = {

    val dead = With(_lbStrict = true, _ubStrict = true).GenArray(shape(alive),
      iv => computeIfDead(sum(tile(value(dim(alive), 3), iv-1, alive)), alive(iv)))

    val reborn = With(_lbStrict = true, _ubStrict = true).GenArray(shape(alive),
      iv => computeIfReborn(sum(tile(value(dim(alive), 3), iv-1, alive)) -
                            sum(tile(value(dim(alive), 3), iv-1, dead)), dead(iv)))

    val result = alive - dead + reborn
    result
  }
}