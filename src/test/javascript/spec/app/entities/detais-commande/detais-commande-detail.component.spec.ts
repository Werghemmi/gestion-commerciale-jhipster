import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisCommandeDetailComponent } from 'app/entities/detais-commande/detais-commande-detail.component';
import { DetaisCommande } from 'app/shared/model/detais-commande.model';

describe('Component Tests', () => {
  describe('DetaisCommande Management Detail Component', () => {
    let comp: DetaisCommandeDetailComponent;
    let fixture: ComponentFixture<DetaisCommandeDetailComponent>;
    const route = ({ data: of({ detaisCommande: new DetaisCommande(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisCommandeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetaisCommandeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetaisCommandeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detaisCommande on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detaisCommande).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
