<?php if ($login): ?>
    <div class="text-end">
        <a href="conta.php">
            <button type="button" class="btn btn-outline-light me-2">Minha Conta</button>
        </a>
        <a href="logout.php">
            <button type="button" class="btn btn-warning">Sair</button>
        </a>
    </div>
    
    
<?php else: ?>
    <div class="text-end">
        <a href="login.php">
            <button type="button" class="btn btn-outline-light me-2">Entrar</button>
        </a>
        <a href="registrar.php">
            <button type="button" class="btn btn-warning">Criar Conta</button>
        </a>
    </div>
<?php endif; ?>