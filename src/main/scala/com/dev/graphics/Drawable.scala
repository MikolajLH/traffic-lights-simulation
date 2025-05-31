package com.dev.graphics

import doodle.image.*

trait Drawable[A]:
  extension (a: A) def toImage: Image