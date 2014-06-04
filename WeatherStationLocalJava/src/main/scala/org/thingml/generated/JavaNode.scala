/**
 * File generated by the ThingML IDE
 * /!\Do not edit this file/!\
 * In case of a bug in the generated code,
 * please submit an issue on our GitHub
 **/

package org.thingml.generated
import org.thingml.generated._
import org.sintef.smac._
import scala.annotation.elidable
import scala.annotation.elidable._
import org.thingml.utils.comm.SerializableTypes._
object Logger {
@elidable(MINIMUM)def debug(s : String) {println("DEBUG:" + s)}
@elidable(INFO)def info(s : String) {println("INFO:" + s)}
@elidable(WARNING)def warning(s : String) {println("WARNING:" + s)}
@elidable(SEVERE)def error(s : String) {println("ERROR:" + s)}
@elidable(MAXIMUM)def severe(s : String) {println("KERNEL PANIC:" + s)}
}
// Definition of Enumeration  DigitalState
object DigitalState_ENUM extends Enumeration {
	type DigitalState_ENUM = Byte
val DIGITALSTATE_LOW : Byte = 0
val DIGITALSTATE_HIGH : Byte = 1
}
object Write_bytes{ def getName = "write_bytes" }
case class Write_bytes(b : Array[Byte], override val name : String = Write_bytes.getName) extends Event(name)/* with java.io.Serializable*/
object Timer_timeout{ def getName = "timer_timeout" }
case class Timer_timeout(override val name : String = Timer_timeout.getName) extends Event(name)/* with java.io.Serializable*/
object Receive_byte{ def getName = "receive_byte" }
case class Receive_byte(b : Byte, override val name : String = Receive_byte.getName) extends Event(name)/* with java.io.Serializable*/
object Timer_start{ def getName = "timer_start" }
case class Timer_start(delay : Int, override val name : String = Timer_start.getName) extends Event(name)/* with java.io.Serializable*/
object Light{ def getName = "light" }
case class Light(light : Int, override val name : String = Light.getName) extends Event(name)/* with java.io.Serializable*/
object Timer_cancel{ def getName = "timer_cancel" }
case class Timer_cancel(override val name : String = Timer_cancel.getName) extends Event(name)/* with java.io.Serializable*/
object Receive_bytes{ def getName = "receive_bytes" }
case class Receive_bytes(b : Array[Byte], override val name : String = Receive_bytes.getName) extends Event(name)/* with java.io.Serializable*/
object Temperature{ def getName = "temperature" }
case class Temperature(temp : Int, override val name : String = Temperature.getName) extends Event(name)/* with java.io.Serializable*/
object Print_value{ def getName = "print_value" }
case class Print_value(v : Int, override val name : String = Print_value.getName) extends Event(name)/* with java.io.Serializable*/
object Write_byte{ def getName = "write_byte" }
case class Write_byte(b : Byte, override val name : String = Write_byte.getName) extends Event(name)/* with java.io.Serializable*/
object ChangeDisplay{ def getName = "changeDisplay" }
case class ChangeDisplay(override val name : String = ChangeDisplay.getName) extends Event(name)/* with java.io.Serializable*/
object Print_message{ def getName = "print_message" }
case class Print_message(msg : String, override val name : String = Print_message.getName) extends Event(name)/* with java.io.Serializable*/

/**
 * Definitions for type : MessageDeserializer
 **/
class MessageDeserializer(val PacketManager_lengthInteger_var : Byte, val PacketManager_lengthString_var : Byte, val PacketManager_lengthUInt16_var : Byte, val PacketManager_MAX_PACKET_SIZE_var : Int, val PacketManager_START_BYTE_var : Byte, val PacketManager_STOP_BYTE_var : Byte, val PacketManager_ESCAPE_BYTE_var : Byte, private var _PacketManager_CODE_POSITION_var : Int, private var _PacketManager_LENGTH_POSITION_var : Int, private var _PacketManager_DATA_POSITION_var : Int, private var _PacketManager_buffer_var : Array[Byte], private var _PacketManager_index_var : Int) extends Component {

//Synchronized accessors of CODE_POSITION:Int
def PacketManager_CODE_POSITION_var:Int = {synchronized{return _PacketManager_CODE_POSITION_var}}
def PacketManager_CODE_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_CODE_POSITION_var = newValue}}

//Synchronized accessors of LENGTH_POSITION:Int
def PacketManager_LENGTH_POSITION_var:Int = {synchronized{return _PacketManager_LENGTH_POSITION_var}}
def PacketManager_LENGTH_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_LENGTH_POSITION_var = newValue}}

//Synchronized accessors of DATA_POSITION:Int
def PacketManager_DATA_POSITION_var:Int = {synchronized{return _PacketManager_DATA_POSITION_var}}
def PacketManager_DATA_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_DATA_POSITION_var = newValue}}

//Synchronized accessors of buffer:Array[Byte]
def PacketManager_buffer_var:Array[Byte] = {synchronized{return _PacketManager_buffer_var}}
def PacketManager_buffer_var_=(newValue : Array[Byte]) { synchronized{ _PacketManager_buffer_var = newValue}}

//Synchronized accessors of index:Int
def PacketManager_index_var:Int = {synchronized{return _PacketManager_index_var}}
def PacketManager_index_var_=(newValue : Int) { synchronized{ _PacketManager_index_var = newValue}}

//Companion object
object MessageDeserializer{
object RemoteControlPort{
def getName = "RemoteControl"
object in {
}
object out {
val temperature_o = Temperature.getName
val light_o = Light.getName
}
}

object inPort{
def getName = "in"
object in {
val receive_bytes_i = Receive_bytes.getName
}
object out {
}
}

object outPort{
def getName = "out"
object in {
}
object out {
val write_bytes_o = Write_bytes.getName
}
}

}

new Port(MessageDeserializer.RemoteControlPort.getName, List(), List(MessageDeserializer.RemoteControlPort.out.temperature_o, MessageDeserializer.RemoteControlPort.out.light_o), this).start
new Port(MessageDeserializer.inPort.getName, List(MessageDeserializer.inPort.in.receive_bytes_i), List(), this).start
new Port(MessageDeserializer.outPort.getName, List(), List(MessageDeserializer.outPort.out.write_bytes_o), this).start
def forward() : Unit = {
Logger.debug("Executing forward ...")
val handler = this
PacketManager_index_var = (PacketManager_DATA_POSITION_var).asInstanceOf[Int]
val code_var : Byte = (PacketManager_buffer_var(PacketManager_CODE_POSITION_var)
).toByte
if(code_var == 10) {
deserializeRemote_temperature()

}
if(code_var == 11) {
deserializeRemote_light()

}
}
def deserializeRemote_temperature() : Unit = {
Logger.debug("Executing deserializeRemote_temperature ...")
val handler = this
val t_var : Int = (deserializeUInt16()
).toInt
Logger.info((("t: " + t_var)).toString)
handler.getPort("RemoteControl") match{
case Some(p) => p.send(new Temperature(t_var))
case None => Logger.warning("no port RemoteControl You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
def deserializeRemote_light() : Unit = {
Logger.debug("Executing deserializeRemote_light ...")
val handler = this
val t_var : Int = (deserializeUInt16()
).toInt
Logger.info((("l: " + t_var)).toString)
handler.getPort("RemoteControl") match{
case Some(p) => p.send(new Light(t_var))
case None => Logger.warning("no port RemoteControl You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
def deserializeInteger() : Int = {
Logger.debug("Executing deserializeInteger ...")
val handler = this
var bytes_var : Array[Byte] = (new Array[Byte](2)).toArray[Byte]
bytes_var(0) = (readByte()
).asInstanceOf[Byte]
bytes_var(1) = (readByte()
).asInstanceOf[Byte]
return bytes_var
.toShort
}
def deserializeUInt16() : Int = {
Logger.debug("Executing deserializeUInt16 ...")
val handler = this
var bytes_var : Array[Byte] = (new Array[Byte](2)).toArray[Byte]
bytes_var(0) = (readByte()
).asInstanceOf[Byte]
bytes_var(1) = (readByte()
).asInstanceOf[Byte]
return bytes_var
.toShort
}
def deserializeString() : String = {
Logger.debug("Executing deserializeString ...")
val handler = this
var result_var : String = ("").toString
val stop_var : Int = (PacketManager_lengthString_var).toInt
var i_var : Int = (0).toInt
while(i_var < stop_var) {
result_var = (result_var + readByte()
.toChar).asInstanceOf[String]
i_var = (i_var + 1).asInstanceOf[Int]

}
return result_var
}
def receive(ArrayDeserializer_receive_bytes_var : Array[Byte]) : Unit = {
Logger.debug("Executing receive ...")
val handler = this
PacketManager_index_var = (0).asInstanceOf[Int]
Logger.info((("receive " + ArrayDeserializer_receive_bytes_var)).toString)
var size_var : Int = (PacketManager_DATA_POSITION_var + ArrayDeserializer_receive_bytes_var(PacketManager_LENGTH_POSITION_var + 1)
 + 1).toInt
Logger.info((("size = " + size_var)).toString)
var i_var : Int = (0).toInt
var current_var : Byte = (ArrayDeserializer_receive_bytes_var(i_var)
).toByte
if(current_var == PacketManager_START_BYTE_var) {
i_var = (i_var + 1).asInstanceOf[Int]
var next_var : Byte = (ArrayDeserializer_receive_bytes_var(i_var)
).toByte
if( !((next_var == PacketManager_STOP_BYTE_var))) {
var continue_var : Boolean = (true).toBoolean
while(continue_var && i_var < size_var) {
Logger.info((("i = " + i_var)).toString)
current_var = (next_var).asInstanceOf[Byte]
i_var = (i_var + 1).asInstanceOf[Int]
next_var = (ArrayDeserializer_receive_bytes_var(i_var)
).asInstanceOf[Byte]
if(current_var == PacketManager_ESCAPE_BYTE_var) {
current_var = (next_var).asInstanceOf[Byte]
i_var = (i_var + 1).asInstanceOf[Int]
size_var = (size_var + 1).asInstanceOf[Int]
next_var = (ArrayDeserializer_receive_bytes_var(i_var)
).asInstanceOf[Byte]

}
storeByte((current_var).toByte)
continue_var = ( !((next_var == PacketManager_STOP_BYTE_var &&  !((current_var == PacketManager_ESCAPE_BYTE_var))))).asInstanceOf[Boolean]

}
storeByte((current_var).toByte)
forward()

}

}
}
def setHeader(PacketManager_setHeader_code_var : Byte, PacketManager_setHeader_length_var : Byte) : Unit = {
Logger.debug("Executing setHeader ...")
val handler = this
PacketManager_index_var = (0).asInstanceOf[Int]
storeByte((1).toByte)
storeByte((0).toByte)
storeByte((0).toByte)
PacketManager_CODE_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
storeByte((PacketManager_setHeader_code_var).toByte)
PacketManager_LENGTH_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
storeByte((PacketManager_setHeader_length_var).toByte)
PacketManager_DATA_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
PacketManager_index_var = (PacketManager_DATA_POSITION_var).asInstanceOf[Int]
}
def storeByte(PacketManager_storeByte_b_var : Byte) : Unit = {
Logger.debug("Executing storeByte ...")
val handler = this
if(PacketManager_index_var == PacketManager_MAX_PACKET_SIZE_var) {
Logger.error((("BUFFER OVERFLOW: " + PacketManager_storeByte_b_var + " has been ignored. Current index = " + PacketManager_index_var)).toString)

}
if(PacketManager_index_var < PacketManager_MAX_PACKET_SIZE_var) {
PacketManager_buffer_var(PacketManager_index_var) = (PacketManager_storeByte_b_var).asInstanceOf[Byte]
PacketManager_index_var = (PacketManager_index_var + 1).asInstanceOf[Int]

}
}
def readByte() : Byte = {
Logger.debug("Executing readByte ...")
val handler = this
var b_var : Byte = (null.asInstanceOf[Byte]).toByte
if(PacketManager_index_var < PacketManager_MAX_PACKET_SIZE_var) {
b_var = (PacketManager_buffer_var(PacketManager_index_var)
).asInstanceOf[Byte]
PacketManager_index_var = (PacketManager_index_var + 1).asInstanceOf[Int]

}
if(PacketManager_index_var == PacketManager_MAX_PACKET_SIZE_var) {
Logger.error((("BUFFER OVERFLOW: trying to read out of buffer boundaries")).toString)
b_var = (PacketManager_STOP_BYTE_var).asInstanceOf[Byte]

}
return b_var
}
def escape() : Array[Byte] = {
Logger.debug("Executing escape ...")
val handler = this
var escaped_var : Array[Byte] = (new Array[Byte](34)).toArray[Byte]
val stop_var : Int = (PacketManager_DATA_POSITION_var + PacketManager_buffer_var(PacketManager_LENGTH_POSITION_var)
).toInt
var i_var : Int = (0).toInt
var j_var : Int = (0).toInt
escaped_var(j_var) = (PacketManager_START_BYTE_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]
var current_var : Byte = (null.asInstanceOf[Byte]).toByte
while(i_var < stop_var) {
current_var = (PacketManager_buffer_var(i_var)
).asInstanceOf[Byte]
if(current_var == PacketManager_START_BYTE_var || current_var == PacketManager_STOP_BYTE_var || current_var == PacketManager_ESCAPE_BYTE_var) {
escaped_var(j_var) = (PacketManager_ESCAPE_BYTE_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]

}
escaped_var(j_var) = (current_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]
i_var = (i_var + 1).asInstanceOf[Int]

}
escaped_var(j_var) = (PacketManager_STOP_BYTE_var).asInstanceOf[Byte]
return escaped_var
}
def send() : Unit = {
Logger.debug("Executing send ...")
val handler = this
handler.getPort("out") match{
case Some(p) => p.send(new Write_bytes(escape()
))
case None => Logger.warning("no port out You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
this.behavior ++= List(new ReceiveStateMachine(false, this).getBehavior)
case class ReceiveStateMachine(keepHistory : Boolean, root : Component) extends StateAction {
override def getBehavior = parent
val parent : StateMachine = new StateMachine(this, keepHistory, root)
override def onEntry() = {
Logger.debug("receive.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("receive.onExit")
//No exit action defined for this state
}

//create sub-states
private val _Idle_state = IdleState()
val Idle_state = new State(_Idle_state, root)
_Idle_state.init
parent.addSubState(Idle_state)
case class IdleState extends StateAction {
override def onEntry() = {
Logger.debug("Idle.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("Idle.onExit")
//No exit action defined for this state
}

def init {
new InternalTransition(getBehavior, new InternalTransition_Idle_26702233(), List((MessageDeserializer.inPort.getName, MessageDeserializer.inPort.in.receive_bytes_i)))
}

case class InternalTransition_Idle_26702233 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_Idle_26702233.executeActions")
receive((getEvent(MessageDeserializer.inPort.in.receive_bytes_i, MessageDeserializer.inPort.getName).get.asInstanceOf[Receive_bytes].b).toArray[Byte])
}

}
}

parent.setInitial(Idle_state)

//create transitions among sub-states
}
}

/**
 * Definitions for type : MessageSerializer
 **/
class MessageSerializer(val PacketManager_lengthInteger_var : Byte, val PacketManager_lengthString_var : Byte, val PacketManager_lengthUInt16_var : Byte, val PacketManager_MAX_PACKET_SIZE_var : Int, val PacketManager_START_BYTE_var : Byte, val PacketManager_STOP_BYTE_var : Byte, val PacketManager_ESCAPE_BYTE_var : Byte, private var _PacketManager_CODE_POSITION_var : Int, private var _PacketManager_LENGTH_POSITION_var : Int, private var _PacketManager_DATA_POSITION_var : Int, private var _PacketManager_buffer_var : Array[Byte], private var _PacketManager_index_var : Int) extends Component {

//Synchronized accessors of CODE_POSITION:Int
def PacketManager_CODE_POSITION_var:Int = {synchronized{return _PacketManager_CODE_POSITION_var}}
def PacketManager_CODE_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_CODE_POSITION_var = newValue}}

//Synchronized accessors of LENGTH_POSITION:Int
def PacketManager_LENGTH_POSITION_var:Int = {synchronized{return _PacketManager_LENGTH_POSITION_var}}
def PacketManager_LENGTH_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_LENGTH_POSITION_var = newValue}}

//Synchronized accessors of DATA_POSITION:Int
def PacketManager_DATA_POSITION_var:Int = {synchronized{return _PacketManager_DATA_POSITION_var}}
def PacketManager_DATA_POSITION_var_=(newValue : Int) { synchronized{ _PacketManager_DATA_POSITION_var = newValue}}

//Synchronized accessors of buffer:Array[Byte]
def PacketManager_buffer_var:Array[Byte] = {synchronized{return _PacketManager_buffer_var}}
def PacketManager_buffer_var_=(newValue : Array[Byte]) { synchronized{ _PacketManager_buffer_var = newValue}}

//Synchronized accessors of index:Int
def PacketManager_index_var:Int = {synchronized{return _PacketManager_index_var}}
def PacketManager_index_var_=(newValue : Int) { synchronized{ _PacketManager_index_var = newValue}}

//Companion object
object MessageSerializer{
object RemoteControlPort{
def getName = "RemoteControl"
object in {
val changeDisplay_i = ChangeDisplay.getName
}
object out {
}
}

object outPort{
def getName = "out"
object in {
}
object out {
val write_bytes_o = Write_bytes.getName
}
}

}

new Port(MessageSerializer.RemoteControlPort.getName, List(MessageSerializer.RemoteControlPort.in.changeDisplay_i), List(), this).start
new Port(MessageSerializer.outPort.getName, List(), List(MessageSerializer.outPort.out.write_bytes_o), this).start
def serializeInteger(SerializerScala_serializeInteger_d_var : Int) : Unit = {
Logger.debug("Executing serializeInteger ...")
val handler = this
val bytes_var : Array[Byte] = (SerializerScala_serializeInteger_d_var.toBytes).toArray[Byte]
storeByte((bytes_var(0)
).toByte)
storeByte((bytes_var(1)
).toByte)
}
def serializeString(SerializerScala_serializeString_d_var : String) : Unit = {
Logger.debug("Executing serializeString ...")
val handler = this
val stop_var : Int = (PacketManager_lengthString_var).toInt
var i_var : Int = (0).toInt
while(i_var < stop_var) {
if(i_var < SerializerScala_serializeString_d_var.size) {
storeByte((SerializerScala_serializeString_d_var(i_var).toByte).toByte)

}
if(i_var > SerializerScala_serializeString_d_var.size - 1) {
storeByte((0).toByte)

}
i_var = (i_var + 1).asInstanceOf[Int]

}
}
def escape() : Array[Byte] = {
Logger.debug("Executing escape ...")
val handler = this
var escaped_var : Array[Byte] = (new Array[Byte](34)).toArray[Byte]
val stop_var : Int = (PacketManager_DATA_POSITION_var + PacketManager_buffer_var(PacketManager_LENGTH_POSITION_var)
).toInt
var i_var : Int = (0).toInt
var j_var : Int = (0).toInt
escaped_var(j_var) = (PacketManager_START_BYTE_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]
var current_var : Byte = (null.asInstanceOf[Byte]).toByte
while(i_var < stop_var) {
current_var = (PacketManager_buffer_var(i_var)
).asInstanceOf[Byte]
if(current_var == PacketManager_START_BYTE_var || current_var == PacketManager_STOP_BYTE_var || current_var == PacketManager_ESCAPE_BYTE_var) {
escaped_var(j_var) = (PacketManager_ESCAPE_BYTE_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]

}
escaped_var(j_var) = (current_var).asInstanceOf[Byte]
j_var = (j_var + 1).asInstanceOf[Int]
i_var = (i_var + 1).asInstanceOf[Int]

}
escaped_var(j_var) = (PacketManager_STOP_BYTE_var).asInstanceOf[Byte]
return escaped_var
}
def send() : Unit = {
Logger.debug("Executing send ...")
val handler = this
handler.getPort("out") match{
case Some(p) => p.send(new Write_bytes(escape()
))
case None => Logger.warning("no port out You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
def setHeader(PacketManager_setHeader_code_var : Byte, PacketManager_setHeader_length_var : Byte) : Unit = {
Logger.debug("Executing setHeader ...")
val handler = this
PacketManager_index_var = (0).asInstanceOf[Int]
storeByte((1).toByte)
storeByte((0).toByte)
storeByte((0).toByte)
PacketManager_CODE_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
storeByte((PacketManager_setHeader_code_var).toByte)
PacketManager_LENGTH_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
storeByte((PacketManager_setHeader_length_var).toByte)
PacketManager_DATA_POSITION_var = (PacketManager_index_var).asInstanceOf[Int]
PacketManager_index_var = (PacketManager_DATA_POSITION_var).asInstanceOf[Int]
}
def storeByte(PacketManager_storeByte_b_var : Byte) : Unit = {
Logger.debug("Executing storeByte ...")
val handler = this
if(PacketManager_index_var == PacketManager_MAX_PACKET_SIZE_var) {
Logger.error((("BUFFER OVERFLOW: " + PacketManager_storeByte_b_var + " has been ignored. Current index = " + PacketManager_index_var)).toString)

}
if(PacketManager_index_var < PacketManager_MAX_PACKET_SIZE_var) {
PacketManager_buffer_var(PacketManager_index_var) = (PacketManager_storeByte_b_var).asInstanceOf[Byte]
PacketManager_index_var = (PacketManager_index_var + 1).asInstanceOf[Int]

}
}
def readByte() : Byte = {
Logger.debug("Executing readByte ...")
val handler = this
var b_var : Byte = (null.asInstanceOf[Byte]).toByte
if(PacketManager_index_var < PacketManager_MAX_PACKET_SIZE_var) {
b_var = (PacketManager_buffer_var(PacketManager_index_var)
).asInstanceOf[Byte]
PacketManager_index_var = (PacketManager_index_var + 1).asInstanceOf[Int]

}
if(PacketManager_index_var == PacketManager_MAX_PACKET_SIZE_var) {
Logger.error((("BUFFER OVERFLOW: trying to read out of buffer boundaries")).toString)
b_var = (PacketManager_STOP_BYTE_var).asInstanceOf[Byte]

}
return b_var
}
this.behavior ++= List(new SerializerBehaviorStateMachine(false, this).getBehavior)
case class SerializerBehaviorStateMachine(keepHistory : Boolean, root : Component) extends StateAction {
override def getBehavior = parent
val parent : StateMachine = new StateMachine(this, keepHistory, root)
override def onEntry() = {
Logger.debug("SerializerBehavior.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("SerializerBehavior.onExit")
//No exit action defined for this state
}

//create sub-states
private val _Serialize_state = SerializeState()
val Serialize_state = new State(_Serialize_state, root)
_Serialize_state.init
parent.addSubState(Serialize_state)
case class SerializeState extends StateAction {
override def onEntry() = {
Logger.debug("Serialize.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("Serialize.onExit")
//No exit action defined for this state
}

def init {
new InternalTransition(getBehavior, new InternalTransition_Serialize_13201178(), List((MessageSerializer.RemoteControlPort.getName, MessageSerializer.RemoteControlPort.in.changeDisplay_i)))
}

case class InternalTransition_Serialize_13201178 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_Serialize_13201178.executeActions")
setHeader((20).toByte, (0).toByte)
send()
}

}
}

parent.setInitial(Serialize_state)

//create transitions among sub-states
}
}

/**
 * Definitions for type : WeatherStation
 **/
class WeatherStation() extends Component {

//Companion object
object WeatherStation{
object RemoteControlInPort{
def getName = "RemoteControlIn"
object in {
val temperature_i = Temperature.getName
val light_i = Light.getName
}
object out {
}
}

object RemoteControlOutPort{
def getName = "RemoteControlOut"
object in {
}
object out {
val changeDisplay_o = ChangeDisplay.getName
}
}

object timerPort{
def getName = "timer"
object in {
val timer_timeout_i = Timer_timeout.getName
}
object out {
val timer_start_o = Timer_start.getName
val timer_cancel_o = Timer_cancel.getName
}
}

}

new Port(WeatherStation.RemoteControlInPort.getName, List(WeatherStation.RemoteControlInPort.in.temperature_i, WeatherStation.RemoteControlInPort.in.light_i), List(), this).start
new Port(WeatherStation.RemoteControlOutPort.getName, List(), List(WeatherStation.RemoteControlOutPort.out.changeDisplay_o), this).start
new Port(WeatherStation.timerPort.getName, List(WeatherStation.timerPort.in.timer_timeout_i), List(WeatherStation.timerPort.out.timer_start_o, WeatherStation.timerPort.out.timer_cancel_o), this).start
this.behavior ++= List(new SensorsDisplayImplStateMachine(false, this).getBehavior)
case class SensorsDisplayImplStateMachine(keepHistory : Boolean, root : Component) extends StateAction {
override def getBehavior = parent
val parent : StateMachine = new StateMachine(this, keepHistory, root)
override def onEntry() = {
Logger.debug("SensorsDisplayImpl.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("SensorsDisplayImpl.onExit")
//No exit action defined for this state
}

//create sub-states
private val _Process_state = ProcessState()
val Process_state = new State(_Process_state, root)
_Process_state.init
parent.addSubState(Process_state)
case class ProcessState extends StateAction {
override def onEntry() = {
Logger.debug("Process.onEntry")
handler.getPort("timer") match{
case Some(p) => p.send(new Timer_start(2000))
case None => Logger.warning("no port timer You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}

override def onExit() = {
Logger.debug("Process.onExit")
//No exit action defined for this state
}

def init {
new InternalTransition(getBehavior, new InternalTransition_Process_21999673(), List((WeatherStation.RemoteControlInPort.getName, WeatherStation.RemoteControlInPort.in.temperature_i)))
new InternalTransition(getBehavior, new InternalTransition_Process_4124754(), List((WeatherStation.RemoteControlInPort.getName, WeatherStation.RemoteControlInPort.in.light_i)))
}

case class InternalTransition_Process_21999673 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_Process_21999673.executeActions")
Logger.info((("Temperature is: " + getEvent(WeatherStation.RemoteControlInPort.in.temperature_i, WeatherStation.RemoteControlInPort.getName).get.asInstanceOf[Temperature].temp)).toString)
}

}
case class InternalTransition_Process_4124754 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_Process_4124754.executeActions")
Logger.info((("Light is: " + getEvent(WeatherStation.RemoteControlInPort.in.light_i, WeatherStation.RemoteControlInPort.getName).get.asInstanceOf[Light].light)).toString)
}

}
}

parent.setInitial(Process_state)

//create transitions among sub-states
val t_Process2Process_1811756 = new Transition(Process_state, Process_state, new TransitionProcess2Process_1811756(), List((WeatherStation.timerPort.getName, WeatherStation.timerPort.in.timer_timeout_i)))
parent.addTransition(t_Process2Process_1811756)
case class TransitionProcess2Process_1811756 extends TransitionAction {
override def executeActions() = {
Logger.debug("t_Process2Process_1811756.executeActions")
Logger.info((("Changing Display on Arduino")).toString)
handler.getPort("RemoteControlOut") match{
case Some(p) => p.send(new ChangeDisplay())
case None => Logger.warning("no port RemoteControlOut You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}

}
}
}

/**
 * Definitions for type : SerialScala
 **/
class SerialScala(val SerialScala_serialPort_var : String) extends Component with org.thingml.comm.rxtx.SerialObserver{

//Companion object
object SerialScala{
object IOStreamPort{
def getName = "IOStream"
object in {
val write_bytes_i = Write_bytes.getName
}
object out {
val receive_bytes_o = Receive_bytes.getName
}
}

}

  var serial : org.thingml.comm.rxtx.Serial4ThingML = null

  new Port(SerialScala.IOStreamPort.getName, List(SerialScala.IOStreamPort.in.write_bytes_i), List(SerialScala.IOStreamPort.out.receive_bytes_o), this).start
override def receive(SerialScala_receive_byte_var : Array[Byte]) : Unit = {
Logger.debug("Executing receive ...")
val handler = this
handler.getPort("IOStream") match{
case Some(p) => p.send(new Receive_bytes(SerialScala_receive_byte_var))
case None => Logger.warning("no port IOStream You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
this.behavior ++= List(new BehaviorStateMachine(false, this).getBehavior)
case class BehaviorStateMachine(keepHistory : Boolean, root : Component) extends StateAction {
override def getBehavior = parent
val parent : StateMachine = new StateMachine(this, keepHistory, root)
override def onEntry() = {
Logger.debug("behavior.onEntry")
serial = new org.thingml.comm.rxtx.Serial4ThingML(SerialScala_serialPort_var, root.asInstanceOf[org.thingml.comm.rxtx.SerialObserver])
}

override def onExit() = {
Logger.debug("behavior.onExit")
//No exit action defined for this state
}

//create sub-states
private val _default_state = DefaultState()
val default_state = new State(_default_state, root)
_default_state.init
parent.addSubState(default_state)
case class DefaultState extends StateAction {
override def onEntry() = {
Logger.debug("default.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("default.onExit")
//No exit action defined for this state
}

def init {
new InternalTransition(getBehavior, new InternalTransition_default_10984059(), List((SerialScala.IOStreamPort.getName, SerialScala.IOStreamPort.in.write_bytes_i)))
}

case class InternalTransition_default_10984059 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_default_10984059.executeActions")
Logger.info((("  serial.write: " + getEvent(SerialScala.IOStreamPort.in.write_bytes_i, SerialScala.IOStreamPort.getName).get.asInstanceOf[Write_bytes].b.mkString("[", ", ", "]"))).toString)
serial.sendData(getEvent(SerialScala.IOStreamPort.in.write_bytes_i, SerialScala.IOStreamPort.getName).get.asInstanceOf[Write_bytes].b)
}

}
}

parent.setInitial(default_state)

//create transitions among sub-states
}
}

/**
 * Definitions for type : TimerScala
 **/
class TimerScala(private var _TimerScala_scalaTimer_var : org.thingml.utils.timer.Timer) extends Component with org.thingml.utils.timer.TimerTask{

//Synchronized accessors of scalaTimer:org.thingml.utils.timer.Timer
def TimerScala_scalaTimer_var:org.thingml.utils.timer.Timer = {synchronized{return _TimerScala_scalaTimer_var}}
def TimerScala_scalaTimer_var_=(newValue : org.thingml.utils.timer.Timer) { synchronized{ _TimerScala_scalaTimer_var = newValue}}

//Companion object
object TimerScala{
object timerPort{
def getName = "timer"
object in {
val timer_start_i = Timer_start.getName
val timer_cancel_i = Timer_cancel.getName
}
object out {
val timer_timeout_o = Timer_timeout.getName
}
}

}

new Port(TimerScala.timerPort.getName, List(TimerScala.timerPort.in.timer_start_i, TimerScala.timerPort.in.timer_cancel_i), List(TimerScala.timerPort.out.timer_timeout_o), this).start
override def onTimeout() : Unit = {
Logger.debug("Executing onTimeout ...")
val handler = this
Logger.info((("timeout ")).toString)
handler.getPort("timer") match{
case Some(p) => p.send(new Timer_timeout())
case None => Logger.warning("no port timer You may consider revising your ThingML model. Or contact the development team if you think it is a bug.")
}
}
def cancel() : Unit = {
Logger.debug("Executing cancel ...")
val handler = this
Logger.info((("cancel ")).toString)
if( !((TimerScala_scalaTimer_var == null))) {
TimerScala_scalaTimer_var ! "cancel"

}
}
def start(TimerScala_start_delay_var : Int) : Unit = {
Logger.debug("Executing start ...")
val handler = this
Logger.info((("start " + TimerScala_start_delay_var)).toString)
cancel()
TimerScala_scalaTimer_var = (new org.thingml.utils.timer.Timer(this,TimerScala_start_delay_var).start).asInstanceOf[org.thingml.utils.timer.Timer]
}
this.behavior ++= List(new SoftTimerStateMachine(false, this).getBehavior)
case class SoftTimerStateMachine(keepHistory : Boolean, root : Component) extends StateAction {
override def getBehavior = parent
val parent : StateMachine = new StateMachine(this, keepHistory, root)
override def onEntry() = {
Logger.debug("SoftTimer.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("SoftTimer.onExit")
//No exit action defined for this state
}

//create sub-states
private val _default_state = DefaultState()
val default_state = new State(_default_state, root)
_default_state.init
parent.addSubState(default_state)
case class DefaultState extends StateAction {
override def onEntry() = {
Logger.debug("default.onEntry")
//No entry action defined for this state
}

override def onExit() = {
Logger.debug("default.onExit")
//No exit action defined for this state
}

def init {
new InternalTransition(getBehavior, new InternalTransition_default_11495600(), List((TimerScala.timerPort.getName, TimerScala.timerPort.in.timer_start_i)))
new InternalTransition(getBehavior, new InternalTransition_default_25391541(), List((TimerScala.timerPort.getName, TimerScala.timerPort.in.timer_cancel_i)))
}

case class InternalTransition_default_11495600 extends InternalTransitionAction {
override def checkGuard() : Boolean = {
getEvent(TimerScala.timerPort.in.timer_start_i, TimerScala.timerPort.getName).get.asInstanceOf[Timer_start].delay > 0
}
override def executeActions() = {
Logger.debug("t_self_default_11495600.executeActions")
start((getEvent(TimerScala.timerPort.in.timer_start_i, TimerScala.timerPort.getName).get.asInstanceOf[Timer_start].delay).toInt)
}

}
case class InternalTransition_default_25391541 extends InternalTransitionAction {
override def executeActions() = {
Logger.debug("t_self_default_25391541.executeActions")
cancel()
}

}
}

parent.setInitial(default_state)

//create transitions among sub-states
}
}

// Initialize instance variables and states

