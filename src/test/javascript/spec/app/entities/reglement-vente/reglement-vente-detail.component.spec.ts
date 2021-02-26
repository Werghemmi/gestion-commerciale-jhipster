import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { ReglementVenteDetailComponent } from 'app/entities/reglement-vente/reglement-vente-detail.component';
import { ReglementVente } from 'app/shared/model/reglement-vente.model';

describe('Component Tests', () => {
  describe('ReglementVente Management Detail Component', () => {
    let comp: ReglementVenteDetailComponent;
    let fixture: ComponentFixture<ReglementVenteDetailComponent>;
    const route = ({ data: of({ reglementVente: new ReglementVente(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [ReglementVenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReglementVenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReglementVenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reglementVente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reglementVente).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
