package controllers;

/**
 * Created by ls.hernandez10 on 22/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import models.WishListEntity;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class WishListController extends Controller
{
    public CompletionStage<Result> getWishLists()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return WishListEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> getWishList(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return WishListEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> deleteWishList(long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.supplyAsync(
                ()->{
                    WishListEntity item = WishListEntity.FINDER.byId(id);
                    WishListEntity.FINDER.deleteById(id);
                    return item != null;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }

    public CompletionStage<Result> createWishList()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        WishListEntity product = Json.fromJson( nProduct , WishListEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    product.save();
                    return product;
                }
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }
}
