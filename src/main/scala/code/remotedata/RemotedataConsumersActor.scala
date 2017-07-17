package code.remotedata

import akka.actor.Actor
import code.actorsystem.ObpActorHelper
import code.consumer.RemotedataConsumersCaseClasses
import code.model.{MappedConsumersProvider, _}
import code.util.Helper.MdcLoggable

class RemotedataConsumersActor extends Actor with ObpActorHelper with MdcLoggable {

  val mapper = MappedConsumersProvider
  val cc = RemotedataConsumersCaseClasses

  def receive = {

    case cc.getConsumerByPrimaryId(id: Long) =>
      logger.debug("getConsumerByPrimaryId(" + id +")")
      sender ! extractResult(mapper.getConsumerByPrimaryId(id))

    case cc.getConsumerByConsumerKey(consumerKey: String) =>
      logger.debug("getConsumerByConsumerKey(" + consumerKey +")")
      sender ! extractResult(mapper.getConsumerByConsumerKey(consumerKey))

    case cc.createConsumer(key: Option[String], secret: Option[String], isActive: Option[Boolean], name: Option[String], appType: Option[AppType.AppType], description: Option[String], developerEmail: Option[String], redirectURL: Option[String], createdByUserId: Option[String]) =>
      logger.debug("createConsumer(" + key.getOrElse("None") + ", " + secret.getOrElse("None") + ", " + isActive.getOrElse("None") + ", " + name.getOrElse("None") + ", " + appType.getOrElse("None") + ", " + description.getOrElse("None") + ", " + developerEmail.getOrElse("None") + ", " + redirectURL.getOrElse("None") + ", " + createdByUserId.getOrElse("None") + ")")
      sender ! extractResult(mapper.createConsumer(key, secret, isActive, name, appType, description, developerEmail, redirectURL, createdByUserId))

    case cc.updateConsumer(consumerId: Long, key: Option[String], secret: Option[String], isActive: Option[Boolean], name: Option[String], appType: Option[AppType.AppType], description: Option[String], developerEmail: Option[String], redirectURL: Option[String], createdByUserId: Option[String]) =>
      logger.debug("createConsumer(" + consumerId + ", " + key.getOrElse("None") + ", " + secret.getOrElse("None") + ", " + isActive.getOrElse("None") + ", " + name.getOrElse("None") + ", " + appType.getOrElse("None") + ", " + description.getOrElse("None") + ", " + developerEmail.getOrElse("None") + ", " + redirectURL.getOrElse("None") + ", " + createdByUserId.getOrElse("None") + ")")
      sender ! extractResult(mapper.updateConsumer(consumerId, key, secret, isActive, name, appType, description, developerEmail, redirectURL, createdByUserId))

    case message => logger.warn("[AKKA ACTOR ERROR - REQUEST NOT RECOGNIZED] " + message)

  }

}


