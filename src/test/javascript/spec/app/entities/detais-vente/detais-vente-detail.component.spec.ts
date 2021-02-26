import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DetaisVenteDetailComponent } from 'app/entities/detais-vente/detais-vente-detail.component';
import { DetaisVente } from 'app/shared/model/detais-vente.model';

describe('Component Tests', () => {
  describe('DetaisVente Management Detail Component', () => {
    let comp: DetaisVenteDetailComponent;
    let fixture: ComponentFixture<DetaisVenteDetailComponent>;
    const route = ({ data: of({ detaisVente: new DetaisVente(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DetaisVenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetaisVenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetaisVenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detaisVente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detaisVente).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
