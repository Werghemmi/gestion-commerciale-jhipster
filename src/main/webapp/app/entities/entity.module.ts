import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.WerghemmiGestionCommerclaieProduitModule),
      },
      {
        path: 'fournisseur',
        loadChildren: () => import('./fournisseur/fournisseur.module').then(m => m.WerghemmiGestionCommerclaieFournisseurModule),
      },
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.WerghemmiGestionCommerclaieClientModule),
      },
      {
        path: 'categorie',
        loadChildren: () => import('./categorie/categorie.module').then(m => m.WerghemmiGestionCommerclaieCategorieModule),
      },
      {
        path: 'taxe',
        loadChildren: () => import('./taxe/taxe.module').then(m => m.WerghemmiGestionCommerclaieTaxeModule),
      },
      {
        path: 'devis',
        loadChildren: () => import('./devis/devis.module').then(m => m.WerghemmiGestionCommerclaieDevisModule),
      },
      {
        path: 'detais-devis',
        loadChildren: () => import('./detais-devis/detais-devis.module').then(m => m.WerghemmiGestionCommerclaieDetaisDevisModule),
      },
      {
        path: 'detais-commande',
        loadChildren: () => import('./detais-commande/detais-commande.module').then(m => m.WerghemmiGestionCommerclaieDetaisCommandeModule),
      },
      {
        path: 'commande',
        loadChildren: () => import('./commande/commande.module').then(m => m.WerghemmiGestionCommerclaieCommandeModule),
      },
      {
        path: 'facture-achat',
        loadChildren: () => import('./facture-achat/facture-achat.module').then(m => m.WerghemmiGestionCommerclaieFactureAchatModule),
      },
      {
        path: 'reglement-achat',
        loadChildren: () => import('./reglement-achat/reglement-achat.module').then(m => m.WerghemmiGestionCommerclaieReglementAchatModule),
      },
      {
        path: 'detais-vente',
        loadChildren: () => import('./detais-vente/detais-vente.module').then(m => m.WerghemmiGestionCommerclaieDetaisVenteModule),
      },
      {
        path: 'vente',
        loadChildren: () => import('./vente/vente.module').then(m => m.WerghemmiGestionCommerclaieVenteModule),
      },
      {
        path: 'facture-vente',
        loadChildren: () => import('./facture-vente/facture-vente.module').then(m => m.WerghemmiGestionCommerclaieFactureVenteModule),
      },
      {
        path: 'reglement-vente',
        loadChildren: () => import('./reglement-vente/reglement-vente.module').then(m => m.WerghemmiGestionCommerclaieReglementVenteModule),
      },
      {
        path: 'mouvement',
        loadChildren: () => import('./mouvement/mouvement.module').then(m => m.WerghemmiGestionCommerclaieMouvementModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class WerghemmiGestionCommerclaieEntityModule {}
