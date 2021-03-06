/**
 * generated by Scrooge 3.0.7-SNAPSHOT
 */
package streamcorpus

import com.twitter.scrooge.{ThriftException, ThriftStruct, ThriftStructCodec}
import org.apache.thrift.protocol._
import java.nio.ByteBuffer
import scala.collection.mutable
import scala.collection.{Map, Set}


object Sentence extends ThriftStructCodec[Sentence] {
  val Struct = new TStruct("Sentence")
  val TokensField = new TField("tokens", TType.LIST, 1)
  val LabelsField = new TField("labels", TType.MAP, 2)

  /**
   * Checks that all required fields are non-null.
   */
  def validate(_item: Sentence) {
  }

  def encode(_item: Sentence, _oproto: TProtocol) { _item.write(_oproto) }
  def decode(_iprot: TProtocol) = Immutable.decode(_iprot)

  def apply(_iprot: TProtocol): Sentence = decode(_iprot)

  def apply(
    tokens: Seq[Token] = Seq(),
    labels: Map[String, Seq[Label]] = Map()
  ): Sentence = new Immutable(
    tokens,
    labels
  )

  def unapply(_item: Sentence): Option[Product2[Seq[Token], Map[String, Seq[Label]]]] = Some(_item)

  object Immutable extends ThriftStructCodec[Sentence] {
    def encode(_item: Sentence, _oproto: TProtocol) { _item.write(_oproto) }
    def decode(_iprot: TProtocol) = {
      var tokens: Seq[Token] = Seq()
      var _got_tokens = false
      var labels: Map[String, Seq[Label]] = Map()
      var _got_labels = false
      var _done = false
      _iprot.readStructBegin()
      while (!_done) {
        val _field = _iprot.readFieldBegin()
        if (_field.`type` == TType.STOP) {
          _done = true
        } else {
          _field.id match {
            case 1 => { /* tokens */
              _field.`type` match {
                case TType.LIST => {
                  tokens = {
                    val _list = _iprot.readListBegin()
                    val _rv = new mutable.ArrayBuffer[Token](_list.size)
                    var _i = 0
                    while (_i < _list.size) {
                      _rv += {
                        Token.decode(_iprot)
                      }
                      _i += 1
                    }
                    _iprot.readListEnd()
                    _rv
                  }
                  _got_tokens = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case 2 => { /* labels */
              _field.`type` match {
                case TType.MAP => {
                  labels = {
                    val _map = _iprot.readMapBegin()
                    val _rv = new mutable.HashMap[String, Seq[Label]]
                    var _i = 0
                    while (_i < _map.size) {
                      val _key = {
                        _iprot.readString()
                      }
                      val _value = {
                        val _list = _iprot.readListBegin()
                        val _rv = new mutable.ArrayBuffer[Label](_list.size)
                        var _i = 0
                        while (_i < _list.size) {
                          _rv += {
                            Label.decode(_iprot)
                          }
                          _i += 1
                        }
                        _iprot.readListEnd()
                        _rv
                      }
                      _rv(_key) = _value
                      _i += 1
                    }
                    _iprot.readMapEnd()
                    _rv
                  }
                  _got_labels = true
                }
                case _ => TProtocolUtil.skip(_iprot, _field.`type`)
              }
            }
            case _ => TProtocolUtil.skip(_iprot, _field.`type`)
          }
          _iprot.readFieldEnd()
        }
      }
      _iprot.readStructEnd()
      new Immutable(
        tokens,
        labels
      )
    }
  }

  /**
   * The default read-only implementation of Sentence.  You typically should not need to
   * directly reference this class; instead, use the Sentence.apply method to construct
   * new instances.
   */
  class Immutable(
    val tokens: Seq[Token] = Seq(),
    val labels: Map[String, Seq[Label]] = Map()
  ) extends Sentence

  /**
   * This Proxy trait allows you to extend the Sentence trait with additional state or
   * behavior and implement the read-only methods from Sentence using an underlying
   * instance.
   */
  trait Proxy extends Sentence {
    protected def _underlying_Sentence: Sentence
    def tokens: Seq[Token] = _underlying_Sentence.tokens
    def labels: Map[String, Seq[Label]] = _underlying_Sentence.labels
  }
}

trait Sentence extends ThriftStruct
  with Product2[Seq[Token], Map[String, Seq[Label]]]
  with java.io.Serializable
{
  import Sentence._

  def tokens: Seq[Token]
  def labels: Map[String, Seq[Label]]

  def _1 = tokens
  def _2 = labels

  override def write(_oprot: TProtocol) {
    Sentence.validate(this)
    _oprot.writeStructBegin(Struct)
    if (true) {
      val tokens_item = tokens
      _oprot.writeFieldBegin(TokensField)
      _oprot.writeListBegin(new TList(TType.STRUCT, tokens_item.size))
      tokens_item.foreach { tokens_item_element =>
        tokens_item_element.write(_oprot)
      }
      _oprot.writeListEnd()
      _oprot.writeFieldEnd()
    }
    if (true) {
      val labels_item = labels
      _oprot.writeFieldBegin(LabelsField)
      _oprot.writeMapBegin(new TMap(TType.STRING, TType.LIST, labels_item.size))
      labels_item.foreach { _pair =>
        val labels_item_key = _pair._1
        val labels_item_value = _pair._2
        _oprot.writeString(labels_item_key)
        _oprot.writeListBegin(new TList(TType.STRUCT, labels_item_value.size))
        labels_item_value.foreach { labels_item_value_element =>
          labels_item_value_element.write(_oprot)
        }
        _oprot.writeListEnd()
      }
      _oprot.writeMapEnd()
      _oprot.writeFieldEnd()
    }
    _oprot.writeFieldStop()
    _oprot.writeStructEnd()
  }

  def copy(
    tokens: Seq[Token] = this.tokens, 
    labels: Map[String, Seq[Label]] = this.labels
  ): Sentence = new Immutable(
    tokens, 
    labels
  )

  override def canEqual(other: Any): Boolean = other.isInstanceOf[Sentence]

  override def equals(other: Any): Boolean = runtime.ScalaRunTime._equals(this, other)

  override def hashCode: Int = runtime.ScalaRunTime._hashCode(this)

  override def toString: String = runtime.ScalaRunTime._toString(this)


  override def productArity: Int = 2

  override def productElement(n: Int): Any = n match {
    case 0 => tokens
    case 1 => labels
    case _ => throw new IndexOutOfBoundsException(n.toString)
  }

  override def productPrefix: String = "Sentence"
}