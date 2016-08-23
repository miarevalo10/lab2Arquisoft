package controllers;

/**
 * Created by ls.hernandez10 on 22/08/2016.
 */
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import static play.libs.Json.toJson;
import models.ProductEntity;
import akka.dispatch.MessageDispatcher;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class ProductController extends Controller
{
    public CompletionStage<Result> getProducts()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return ProductEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> getProduct(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return ProductEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> createProduct()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nProduct = request().body().asJson();
        ProductEntity product = Json.fromJson( nProduct , ProductEntity.class ) ;
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

    public CompletionStage<Result> deleteProduct(Long id)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            ProductEntity.FINDER.deleteById(id);
                            return id;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }

    public CompletionStage<Result> upadateProduct(Long id, ProductEntity p)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {

                            ProductEntity pPorActualizar =  ProductEntity.FINDER.byId(id);
                            pPorActualizar.setName(p.getName());
                            pPorActualizar.setAvailable(p.getAvailable());
                            pPorActualizar.setPrice(p.getPrice());
                            pPorActualizar.setStock(p.getStock());
                            return pPorActualizar;
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        productEntities -> {
                            return ok(toJson(productEntities));
                        }
                );
    }


}
