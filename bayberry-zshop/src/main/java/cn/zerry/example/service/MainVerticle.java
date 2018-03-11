package cn.zerry.example.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;

/**
 * @author linzengrui
 * @Description TODO
 * @date 2018年03月10日 21:56
 */
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        Future<String> dbVerticleDeployment = Future.future();
        vertx.deployVerticle(new WikiDatabaseVerticle(), dbVerticleDeployment.completer());

        dbVerticleDeployment.compose(id -> {

            Future<String> httpVerticleDeployment = Future.future();
            vertx.deployVerticle(
                    "cn.zerry.example.service.HttpServerVerticle",
            new DeploymentOptions().setInstances(2),
            httpVerticleDeployment.completer());

            return httpVerticleDeployment;

        }).setHandler(ar -> {
            if (ar.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(ar.cause());
            }
        });
    }
}
